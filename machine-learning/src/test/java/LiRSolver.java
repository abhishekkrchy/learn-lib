import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import lombok.extern.slf4j.Slf4j;
import models.Model;
import solvers.Solver;
import solvers.regression.RegressionSolver;
import solvers.regression.linear.LinearRegressionSolver;
import util.constants.enums.Regularizer;

@Slf4j
public class LiRSolver {

    public static void main(String[] args) throws Exception {
        RegressionSolver lrs = new LinearRegressionSolver();
        lrs.setNumberOfExamples(3);
        lrs.setNumberOfVariables(4);
        lrs.setMaxIterations(5);
        lrs.setRegularizer(Regularizer.L2);
        lrs.setRegularizationCoefficient(0.03);
        lrs.loadDataSet("/Users/abhishek.kumar/learn-lib/machine-learning/src/test/resources/data.csv", false);
        lrs.assignTrainAndTest(25, false);
        Solver solver = lrs.solve();
        Model model = solver.getModel();
        DenseVector predictions = model.predict(lrs.getTestingX());
        System.out.println("Predictions : " + predictions);
        System.out.println(" Regression mse : " + Errors.ERROR_FUNCTION.apply(ErrorType.MSE).apply((DenseVector) lrs.getTestingY(), predictions));
    }
}
