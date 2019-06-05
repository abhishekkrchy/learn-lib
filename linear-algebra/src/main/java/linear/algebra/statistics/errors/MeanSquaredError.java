package linear.algebra.statistics.errors;

import linear.algebra.util.Polynomial;
import linear.algebra.util.constants.enums.AlgebraicFunction;
import linear.algebra.vectors.dense.DenseVector;

import java.util.Arrays;

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
        // TODO : check on varPos and size & more readable
        double total = 0.0;
        Polynomial childNode = null;
        System.out.println(Arrays.toString(denseVector1.stream().toArray()));
        System.out.println(Arrays.toString(denseVector2.stream().toArray()));
        for (int i = 0; i < denseVector1.size(); i++) {
            if (i != varPos) {
                total += Math.pow(denseVector1.value(i) - denseVector2.value(i), 2.0);
            } else {
                Polynomial sub2 = new Polynomial(2, 1);
                System.out.println(sub2);
                Polynomial sub = new Polynomial(0, denseVector1.value(i) * denseVector1.value(i), AlgebraicFunction.ADD, sub2);
                System.out.println(sub);
                Polynomial subF = new Polynomial(1, -2 * (denseVector1.value(i)), AlgebraicFunction.ADD, sub);
                System.out.println(subF);
                childNode = new Polynomial(0, 1.0 / (denseVector1.size()), AlgebraicFunction.MUL, subF);
                System.out.println(childNode);
            }
        }
        System.out.println("total " + total);
        return new Polynomial(0, total / denseVector1.size(), AlgebraicFunction.ADD, childNode);
    }
}
