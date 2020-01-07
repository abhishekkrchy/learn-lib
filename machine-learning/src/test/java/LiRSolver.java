import linear.algebra.util.constants.enums.ErrorType;
import models.Model;
import solvers.Solver;
import solvers.regression.RegressionSolver;
import solvers.regression.linear.LinearRegressionSolver;
import util.constants.enums.OptimizerType;
import util.constants.enums.Regularizer;

public class LiRSolver {
    public static void main(String[] args) {

        RegressionSolver lrs = new LinearRegressionSolver();
       // lrs.setErrorType(ErrorType.MSE);
        //lrs.setFactors();
        //lrs.setLearningRate(0.01);
        //lrs.setMaxIterations(10);
        //lrs.setMinDescentLimit(0.0001);
        //lrs.setOptimizerTypeType(OptimizerType.GRADIENT_DESCENT);
       // lrs.setRegularizationCoefficient(0.5);
        lrs.setNumberOfExamples(3);
        lrs.setNumberOfVariables(4);
        lrs.setMaxIterations(5);
        lrs.setRegularizer(Regularizer.L2);
        lrs.setRegularizationCoefficient(0.03);
        lrs.setTrainingX(new double[][]{
                new double[] {1,2,3},
                new double[] {4,5,6},
                new double[] {7,8,9}});
        lrs.setTrainingY(new double[] {111,332,246});
        lrs.setTestingX(new double[][]{new double[] {10,11,12}});
        lrs.setTestingY(new double[]{432});
        Solver solver = lrs.solve();
        Model model =  solver.getModel();
       // model.predict(



    }
}
