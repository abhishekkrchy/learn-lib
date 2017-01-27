package linear.algebra.statistics.errors;

import linear.algebra.util.MarkedNode;
import linear.algebra.util.constants.enums.AlgebraicFunction;
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
    public static MarkedNode error(DenseVector denseVector1, DenseVector denseVector2, int varPos) {
        double total = 0.0;
        MarkedNode markedNode = new MarkedNode();
        for (int i = 0; i < denseVector1.size(); i++) {
            if (i == varPos) {
                MarkedNode temp = new MarkedNode(-1, denseVector1.value(i));
                markedNode.setChildNode(temp);
            } else {
                total += Math.pow(denseVector1.value(i) - denseVector2.value(i), 2.0);
            }
        }
        markedNode.setAdditiveConstant(total / denseVector1.size());
        markedNode.setChildNodeExponent(2.0);
        markedNode.setChildNodeMultiplicand(1 / (denseVector1.size()));
        markedNode.setChildFunctionalRelation(AlgebraicFunction.ADD);
        return markedNode;
    }
}
