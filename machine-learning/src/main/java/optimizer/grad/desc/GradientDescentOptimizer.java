package optimizer.grad.desc;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.Vectors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import models.Model;
import models.RegressionModel;
import optimizer.Optimizer;
import optimizer.functions.Functions;
import util.constants.enums.Regularizer;

import static linear.algebra.Utils.multiply;

public class GradientDescentOptimizer implements Optimizer{
    private DenseVector initial;
    private int maxIterations;
    private DenseMatrix trainingX;
    private ErrorType errorType;
    private DenseVector trainingY;
    private Regularizer regularizer;
    private double regularizationCoefficient;
    private double learningRate;
    private double minDescentLimit;
    private DenseVector errorVector;

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

    @Override
    public Model optimize() {
        int iterations = 0;
        double[] errors = new double[maxIterations];
        try {
            errors[iterations] = Errors.MARKED_ERROR_FUNCTION.apply(errorType).apply(0)
                    .apply(Vectors.toDenseVector(multiply(trainingX, initial.slice(1, initial.size())).stream().map(x -> x + initial.value(1))),
                            trainingY)
                    .value(initial.value(1));
            DenseVector denseVector = initial;
            while (iterations < maxIterations) {
                double val = denseVector.value(0);
                double changeFactor = Functions.lossFunction(Vectors.toDenseVector(multiply(trainingX, denseVector.slice(1, denseVector.size())).stream().map(x -> x + val)),
                        trainingY, regularizer, regularizationCoefficient, errorType, 1)
                        .derivative(initial.value(1));
                denseVector = Vectors.toDenseVector(denseVector.stream().map(x -> x - (changeFactor * learningRate)));

                errors[++iterations] = Errors.MARKED_ERROR_FUNCTION.apply(errorType).apply(1)
                        .apply(Vectors.toDenseVector(multiply(trainingX, denseVector.slice(1,denseVector.size())).stream().map(x -> x + val)),
                                trainingY)
                        .value(denseVector.value(1));
                if (Math.abs(errors[iterations] - errors[iterations - 1]) <= minDescentLimit)
                    break;
            }
            errorVector = new DenseVector(errors);
            RegressionModel model=new RegressionModel();
            model.setFactors(denseVector.slice(1,denseVector.size()));
            model.setIntercept(denseVector.value(0));
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
