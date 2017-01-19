package optimizer;

import linear.algebra.vectors.dense.DenseVector;
import models.regression.RegressionModel;
import optimizer.grad.desc.GradientDescent;
import util.constants.enums.Optimizer;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Optimizers {
    public static Function<Optimizer, BiFunction<DenseVector, RegressionModel, DenseVector>> optimize =
            optimizer -> (BiFunction<DenseVector, RegressionModel, DenseVector>) (denseVector, regressionModel) -> {
                switch (optimizer) {
                    case GRADIENT_DESCENT:
                    default:
                        return GradientDescent.iterate(denseVector, regressionModel);
                }
            };
}
