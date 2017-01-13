package statistics.util;

import linear.algebra.matrices.util.constants.enums.AlgebraicFunction;
import linear.algebra.matrices.util.MarkedNode;
import linear.algebra.vectors.dense.DenseVector;

import java.util.Random;

public class Statistics {
    public static double meanSquaredError(DenseVector denseVector1, DenseVector denseVector2) {
        double total = 0.0;
        for (int i = 0; i < denseVector1.size(); i++) {
            total += Math.pow(denseVector1.value(i) - denseVector2.value(i), 2.0);
        }
        return total / denseVector1.size();
    }

    public static MarkedNode meanSquaredError(DenseVector denseVector1, DenseVector denseVector2, int varPos) {
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

    public static DenseVector getNormalDistributionSamples(int numberOfSamples) {
        Random random = new Random(System.currentTimeMillis());
        double[] normalDistribution = new double[numberOfSamples];
        for (int i = 0; i < numberOfSamples; i++) {
            normalDistribution[i] = random.nextGaussian();
        }
        return new DenseVector(normalDistribution);
    }
}
