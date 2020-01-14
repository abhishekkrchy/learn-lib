package learn.lib.examples;

import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import lombok.extern.slf4j.Slf4j;
import models.Model;
import solvers.Solver;
import solvers.regression.RegressionSolver;
import util.constants.enums.Regularizer;

@Slf4j
public class LinearRegressionExample {
    public static void main(String[] args) {
        Solver linearRegressionSolver = RegressionSolver.builder()
                .errorType(ErrorType.MSE)
                .regularizer(Regularizer.L2)
                .maxIterations(5)
                .inputFile(LinearRegressionExample.class.getClassLoader().getResource("data.csv").getPath())
                .build();

        Model model = linearRegressionSolver.solve();
        DenseVector predictions = model.predict(linearRegressionSolver.getData().testingX());
        log.info("Predictions for the test data {} are {}", linearRegressionSolver.getData().testingY(), predictions);
        log.info("Regression Error comes out to be : {}", Errors.type(ErrorType.MSE).apply(linearRegressionSolver.getData().testingY(), predictions));
    }
}
