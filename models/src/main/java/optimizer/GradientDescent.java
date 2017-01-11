package optimizer;

import linear.algebra.vectors.dense.DenseVector;
import models.regression.RegressionModel;
import optimizer.functions.Calculator;
import optimizer.functions.Functions;

import static linear.algebra.Utils.multiply;

/**
 * Created by abhishek on 3/1/17.
 */
public class GradientDescent {
    public static DenseVector iterate(DenseVector initial, RegressionModel regressionModel) throws Exception {
        int iterations = 0;
        double[] errors = new double[regressionModel.getMaxIterations()];
        errors[iterations] = Functions.meanSquaredError(new Calculator(multiply(regressionModel.getTrainingX(), initial).map(x -> x + initial.value(0)), regressionModel.getTrainingY(), regressionModel.getRegularizationFunction(), regressionModel.getRegularizationCoefficient()), 0).calc(initial.value(0));
        while (iterations < regressionModel.getMaxIterations()) {
            double changeFactor = Functions.lossFunction(new Calculator(multiply(regressionModel.getTrainingX(), initial).map(x -> x + initial.value(0)), regressionModel.getTrainingY(), regressionModel.getRegularizationFunction(), regressionModel.getRegularizationCoefficient()), 0).derivative(initial.value(0));
            DenseVector denseVector = initial.map(x -> x - (changeFactor * regressionModel.getLearningRate()));
            errors[++iterations] = Functions.meanSquaredError(new Calculator(multiply(regressionModel.getTrainingX(), denseVector).map(x -> x + denseVector.value(0)), regressionModel.getTrainingY(), regressionModel.getRegularizationFunction(), regressionModel.getRegularizationCoefficient()), 0).calc(denseVector.value(0));
        }
        return new DenseVector(errors);
    }
}
