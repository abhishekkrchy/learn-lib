package optimizer.functions;

import linear.algebra.vectors.dense.DenseVector;
import util.constants.enums.Regularizer;

/**
 * The type Regularizers.
 */
public class Regularizers {

    private Regularizers() {

    }

    /**
     * Regularize the given denseVector based
     * on {@link Regularizer} regularizer.
     *
     * @param vector                    the vector
     * @param regularizer               the regularizer
     * @param regularizationCoefficient the regularization coefficient
     * @return the marked node
     */
    public static double regularize(DenseVector vector, Regularizer regularizer, double regularizationCoefficient) {
        switch (regularizer) {
            case L1:
                return regularizationCoefficient * vector.tail().stream().map(Math::abs).sum();
            case L2:
                return 0.5 * regularizationCoefficient * vector.tail().stream().map(x -> Math.pow(x, 2)).sum();
            default:
                return 0;

        }
    }
}
