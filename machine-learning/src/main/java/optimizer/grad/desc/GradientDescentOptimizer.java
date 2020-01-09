package optimizer.grad.desc;

import linear.algebra.expressions.Polynomial;
import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import lombok.extern.slf4j.Slf4j;
import models.Model;
import models.RegressionModel;
import optimizer.Optimizer;
import optimizer.functions.Regularizers;
import util.constants.enums.Regularizer;

import java.util.Arrays;

@Slf4j
public class GradientDescentOptimizer implements Optimizer {

    private DenseVector initial;
    private int maxIterations;
    private DenseMatrix trainingX;
    private ErrorType errorType;
    private DenseVector trainingY;
    private Regularizer regularizer;
    private double regularizationCoefficient;
    private double learningRate;
    private double minDescentLimit;

    public GradientDescentOptimizer(DenseVector initial, int maxIterations, DenseMatrix trainingX, ErrorType errorType, DenseVector trainingY, Regularizer regularizer, double regularizationCoefficient, double learningRate, double minDescentLimit) {
        this.initial = initial;
        this.maxIterations = maxIterations;
        this.trainingX = trainingX;
        this.errorType = errorType;
        this.trainingY = trainingY;
        this.regularizer = regularizer;
        this.regularizationCoefficient = regularizationCoefficient;
        this.learningRate = learningRate;
        this.minDescentLimit = minDescentLimit;
    }

    private Polynomial[] polynomialError(DenseMatrix training, DenseVector factors, int varPos, DenseVector trainingY) {
        Polynomial[] products = training.addColumn(1).multiplyWithVariable(factors, varPos);
        for (int i = 0; i < trainingY.size(); i++) {
            products[i].term(-1 * trainingY.value(i));
        }
        return Arrays.stream(products).map(Polynomial::squared).map(x -> x.divideCoefficients(products.length)).toArray(Polynomial[]::new);
    }

    private DenseVector findDelta(DenseVector factors) {
        DenseVector delta = new DenseVector(factors.size());
        for (int i = 0; i < factors.size(); i++) {
            Polynomial loss = Arrays.stream(polynomialError(trainingX, factors, i, trainingY)).reduce(new Polynomial(2), Polynomial::add);
            // TODO : check
            loss.term(Regularizers.regularize(factors, regularizer, regularizationCoefficient));
            log.debug("factors, i & polynomial errors and loss :{} --  {} --- {}  --- {}", factors, i, Arrays.toString(polynomialError(trainingX, factors, i, trainingY)), loss);
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
        return Errors.type(errorType).apply(trainingX.multiplyAndAddIntercept(factors), trainingY);
    }


    @Override
    public Model optimize() {
        log.debug("trainingX,trainingY,testX & testY : {} {}", trainingX, trainingY);
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
