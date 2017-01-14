package optimizer;

import linear.algebra.util.Vectors;
import linear.algebra.vectors.dense.DenseVector;
import models.regression.RegressionModel;
import optimizer.functions.Functions;

import static linear.algebra.Utils.multiply;
import static linear.algebra.statistics.Statistics.meanSquaredError;

public class GradientDescent {
    public static DenseVector iterate(DenseVector initial, RegressionModel regressionModel) throws Exception {
        int iterations = 0;
        double[] errors = new double[regressionModel.getMaxIterations()];
        errors[iterations] = meanSquaredError(Vectors.toDenseVector(multiply(regressionModel.getTrainingX(), initial).stream().map(x -> x + initial.value(0))), regressionModel.getTrainingY(), 0).calc(initial.value(0));
        while (iterations < regressionModel.getMaxIterations()) {
            double changeFactor = Functions.lossFunction(Vectors.toDenseVector(multiply(regressionModel.getTrainingX(), initial).stream().map(x -> x + initial.value(0))), regressionModel.getTrainingY(), regressionModel.getRegularizationFunction(), regressionModel.getRegularizationCoefficient(), 0).derivative(initial.value(0));
            DenseVector denseVector = Vectors.toDenseVector(initial.stream().map(x -> x - (changeFactor * regressionModel.getLearningRate())));
            errors[++iterations] = meanSquaredError(Vectors.toDenseVector(multiply(regressionModel.getTrainingX(), denseVector).stream().map(x -> x + denseVector.value(0))), regressionModel.getTrainingY(), 0).calc(denseVector.value(0));
            if (Math.abs(errors[iterations] - errors[iterations - 1]) <= regressionModel.getMinDescentLimit())
                break;
        }
        return new DenseVector(errors);
    }
}
