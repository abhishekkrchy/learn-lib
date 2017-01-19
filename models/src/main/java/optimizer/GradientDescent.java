package optimizer;

import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.Vectors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import models.regression.RegressionModel;
import optimizer.functions.Functions;

import static linear.algebra.Utils.multiply;

public class GradientDescent {
    public static DenseVector iterate(DenseVector initial, RegressionModel regressionModel) throws Exception {
        int iterations = 0;
        double[] errors = new double[regressionModel.getMaxIterations()];
        errors[iterations] = Errors.MARKED_ERROR_FUNCTION.apply(ErrorType.MSE).apply(0)
                .apply(Vectors.toDenseVector(multiply(regressionModel.getTrainingX(), initial).stream().map(x -> x + initial.value(0))),
                        regressionModel.getTrainingY())
                .calc(initial.value(0));
        while (iterations < regressionModel.getMaxIterations()) {
            double changeFactor = Functions.lossFunction(Vectors.toDenseVector(multiply(regressionModel.getTrainingX(), initial).stream().map(x -> x + initial.value(0))),
                    regressionModel.getTrainingY(), regressionModel.getRegularizer(), regressionModel.getRegularizationCoefficient(), regressionModel.getErrorType(), 0)
                    .derivative(initial.value(0));
            DenseVector denseVector = Vectors.toDenseVector(initial.stream().map(x -> x - (changeFactor * regressionModel.getLearningRate())));
            errors[++iterations] = Errors.MARKED_ERROR_FUNCTION.apply(ErrorType.MSE).apply(0)
                    .apply(Vectors.toDenseVector(multiply(regressionModel.getTrainingX(), denseVector).stream().map(x -> x + denseVector.value(0))),
                            regressionModel.getTrainingY())
                    .calc(denseVector.value(0));
            if (Math.abs(errors[iterations] - errors[iterations - 1]) <= regressionModel.getMinDescentLimit())
                break;
        }
        return new DenseVector(errors);
    }
}
