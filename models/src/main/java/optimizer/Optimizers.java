package optimizer;

import linear.algebra.vectors.dense.DenseVector;
import solvers.regression.RegressionSolver;
import optimizer.grad.desc.GradientDescent;
import util.constants.enums.Optimizer;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Optimizers {
    public static Function<Optimizer, BiFunction<DenseVector, RegressionSolver, DenseVector>> optimize =
            optimizer -> (BiFunction<DenseVector, RegressionSolver, DenseVector>) (denseVector, regressionModel) -> {
                switch (optimizer) {
                    case GRADIENT_DESCENT:
                    default:
                        return GradientDescent.iterate(denseVector, regressionModel);
                }
            };
}
