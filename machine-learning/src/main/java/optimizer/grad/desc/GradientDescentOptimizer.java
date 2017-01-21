package optimizer.grad.desc;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.Vectors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
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
    public void optimize() {
        int iterations = 0;
        double[] errors = new double[maxIterations];
        try {
            errors[iterations] = Errors.MARKED_ERROR_FUNCTION.apply(errorType).apply(0)
                    .apply(Vectors.toDenseVector(multiply(trainingX, initial).stream().map(x -> x + initial.value(0))),
                            trainingY)
                    .calc(initial.value(0));
            while (iterations < maxIterations) {
                double changeFactor = Functions.lossFunction(Vectors.toDenseVector(multiply(trainingX, initial).stream().map(x -> x + initial.value(0))),
                        trainingY, regularizer, regularizationCoefficient, errorType, 0)
                        .derivative(initial.value(0));
                DenseVector denseVector = Vectors.toDenseVector(initial.stream().map(x -> x - (changeFactor * learningRate)));
                errors[++iterations] = Errors.MARKED_ERROR_FUNCTION.apply(errorType).apply(0)
                        .apply(Vectors.toDenseVector(multiply(trainingX, denseVector).stream().map(x -> x + denseVector.value(0))),
                                trainingY)
                        .calc(denseVector.value(0));
                if (Math.abs(errors[iterations] - errors[iterations - 1]) <= minDescentLimit)
                    break;
            }
            errorVector=new DenseVector(errors);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
