package optimizer.grad.desc;

import linear.algebra.expressions.Polynomial;
import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import lombok.extern.slf4j.Slf4j;
import models.Model;
import models.RegressionModel;
import optimizer.Optimizer;
import optimizer.functions.Functions;
import util.Data;
import util.constants.enums.Regularizer;

@Slf4j
public class GradientDescentOptimizer implements Optimizer {

    private DenseVector initial;
    private int maxIterations;
    private ErrorType errorType;
    private Data data;
    private Regularizer regularizer;
    private double regularizationCoefficient;
    private double learningRate;
    private double minDescentLimit;

    public GradientDescentOptimizer(DenseVector initial, int maxIterations, ErrorType errorType, Data data, Regularizer regularizer, double regularizationCoefficient, double learningRate, double minDescentLimit) {
        this.initial = initial;
        this.maxIterations = maxIterations;
        this.errorType = errorType;
        this.data = data;
        this.regularizer = regularizer;
        this.regularizationCoefficient = regularizationCoefficient;
        this.learningRate = learningRate;
        this.minDescentLimit = minDescentLimit;
    }

    private DenseVector findDelta(DenseVector factors) {
        DenseVector delta = new DenseVector(factors.size());
        for (int i = 0; i < factors.size(); i++) {
            Polynomial loss = Functions.markedLossFunction(data, factors, regularizer, regularizationCoefficient, errorType, i);
            log.debug("factors, index and loss : {} --  {} --- {}", factors, i, loss);
            double derivativeValue = loss.derivative(factors.value(i));
            log.debug("Loss function derivative is : {} {}", loss.derivative(), derivativeValue);
            delta.setValue(i, derivativeValue);
        }
        return delta;
    }

    private DenseVector newFactors(DenseVector oldFactors, DenseVector delta) {
        DenseVector newFactors = new DenseVector(oldFactors.size());
        for (int i = 0; i < oldFactors.size(); i++) {
            newFactors.setValue(i, oldFactors.value(i) - (learningRate * delta.value(i)));
        }
        return newFactors;
    }

    private double error(ErrorType errorType, DenseVector factors) {
        return Errors.type(errorType).apply(data.trainingX().multiplyAndAddIntercept(factors), data.trainingY());
    }


    @Override
    public Model optimize() {
        log.debug("trainingX,trainingY,testX & testY : {} {}", data.trainingX(), data.trainingY());
        int iterations = 0;
        DenseVector factors = initial;

        DenseVector errors = new DenseVector(maxIterations + 1);
        errors.setValue(0, error(errorType, factors));

        while (iterations < maxIterations) {
            log.debug("Iteration number {}", iterations);
            DenseVector delta = findDelta(factors);
            factors = newFactors(factors, delta);
            errors.setValue(++iterations, error(errorType, factors));
            log.info("New factors : {} and delta : {} and errors : {}", factors, delta, errors);
            if (Math.abs(errors.value(iterations) - errors.value(iterations - 1)) <= minDescentLimit) {
                log.info("Min descent limit reached");
                break;
            }
        }
        return new RegressionModel(factors);
    }
}
