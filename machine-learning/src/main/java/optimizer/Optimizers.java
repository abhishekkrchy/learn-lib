package optimizer;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import optimizer.grad.desc.GradientDescentOptimizer;
import util.constants.enums.OptimizerType;
import util.constants.enums.Regularizer;

/**
 * The Optimizers utility
 * methods for classes and/or objects of
 * types implementing {@link Optimizer} interface.
 */
public class Optimizers {

    public static Optimizer optimizer(OptimizerType optimizerType, DenseVector initial, int maxIterations, DenseMatrix trainingX, ErrorType errorType, DenseVector trainingY, Regularizer regularizer, double regularizationCoefficient, double learningRate, double minDescentLimit) {
        switch (optimizerType) {
            case GRADIENT_DESCENT:
                return new GradientDescentOptimizer(initial, maxIterations, trainingX, errorType, trainingY, regularizer, regularizationCoefficient, learningRate, minDescentLimit);
            default:
                throw new IllegalArgumentException("Invalid optimizer type.");
        }
    }
}
