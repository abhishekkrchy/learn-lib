package linear.algebra.statistics.errors;

import linear.algebra.expressions.Polynomial;
import linear.algebra.vectors.dense.DenseVector;

/**
 * The type Mean squared error.
 * TODO :: move.
 */
class MeanSquaredError {
    /**
     * Error as double.
     *
     * @param denseVector1 the dense vector 1
     * @param denseVector2 the dense vector 2
     * @return the double
     */
    public static double error(DenseVector denseVector1, DenseVector denseVector2) {
        double total = 0.0;
        for (int i = 0; i < denseVector1.size(); i++) {
            total += Math.pow(denseVector1.value(i) - denseVector2.value(i), 2.0);
        }
        return total / denseVector1.size();
    }

    /**
     * Error as marked node.
     *
     * @param denseVector1 the dense vector 1
     * @param denseVector2 the dense vector 2
     * @param varPos       the var pos
     * @return the marked node
     */
    public static Polynomial error(DenseVector denseVector1, DenseVector denseVector2, int varPos) {
        Polynomial polynomial = new Polynomial(2);

        double total = 0.0;
        for (int i = 0; i < denseVector1.size(); i++) {
            if (i != varPos) {
                total += Math.pow(denseVector1.value(i) - denseVector2.value(i), 2.0);
            } else {
                total += denseVector1.value(i) * denseVector1.value(i);
                polynomial.term((-2d / denseVector1.size()) * denseVector1.value(i), 1).term(1d / denseVector1.size(), 2);
            }
        }
        return polynomial.term(total / denseVector1.size());
    }
}
