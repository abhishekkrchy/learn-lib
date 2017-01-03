package optimizer;

import linear.algebra.matrices.dense.TwoDimensionalMatrix;
import linear.algebra.vectors.dense.DenseVector;
import models.regression.RegressionModel;
import statistics.CostFunctions;

import static linear.algebra.Utils.multiply;

/**
 * Created by abhishek on 3/1/17.
 */
public class GradientDescent {
    public static DenseVector iterate(DenseVector initial, RegressionModel regressionModel) throws Exception {
        int iterations=0;
        double[] errors=new double[regressionModel.getMaxIterations()];
        errors[iterations]= CostFunctions.meanSquaredError(multiply(regressionModel.getTrainingX(),initial).map(x -> x + initial.value(0)),regressionModel.getTrainingY());
        TwoDimensionalMatrix trainingXMatrix=regressionModel.getTrainingX();
       // DenseVector initialPrediction=new DenseVector(Arrays.copyOfRange(initial,1,initial.length));
        /*DenseVector predictionLocal=multiply(regressionModel.getTrainingX(),initialPrediction).map(x -> x + initial[0]);*/
        /*predictions.map(x -> x + initial[0]);*/
        while (iterations>regressionModel.getMaxIterations()||errors[iterations]<=regressionModel.getMinDescentLimit()){
            iterations++;

        }
        return new DenseVector(errors);
    }
}
