package optimizer.functions;

import linear.algebra.vectors.dense.DenseVector;
import util.constants.enums.Regularizer;

/**
 * The type Regularizers.
 */
public class Regularizers {
    /**
     * Regularize the given denseVector based
     * on {@link Regularizer} regularizer.
     *
     * @param denseVector2              the dense vector 2
     * @param regularizer               the regularizer
     * @param regularizationCoefficient the regularization coefficient
     * @return the marked node
     */
    public static double regularize(DenseVector denseVector2, Regularizer regularizer, double regularizationCoefficient) {
        double constant = 0;
        switch (regularizer) {
            case L1:
                constant += regularizationCoefficient * (denseVector2.slice(1, denseVector2.size()).stream().map(Math::abs).reduce(0.0, Double::sum));
                break;
            case L2:
                constant += 0.5 * regularizationCoefficient * (denseVector2.slice(1, denseVector2.size()).stream().map(x -> Math.pow(x, 2)).reduce(0.0, Double::sum));
                break;
            default:
                break;

        }
        return constant;
    }
}
