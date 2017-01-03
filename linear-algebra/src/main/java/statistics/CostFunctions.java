package statistics;

import linear.algebra.vectors.dense.DenseVector;

/**
 * Created by abhishek on 4/1/17.
 */
public class CostFunctions {
    public static double meanSquaredError(DenseVector denseVector1, DenseVector denseVector2) {
        double total = 0.0;
        for (int i = 0; i < denseVector1.getSize(); i++) {
            total += Math.pow(denseVector1.value(i) - denseVector2.value(i), 2.0);
        }
        return total / denseVector1.getSize();
    }
}
