package optimizer.functions;

import linear.algebra.matrices.util.constants.enums.AlgebraicFunction;
import linear.algebra.matrices.util.MarkedNode;
import linear.algebra.vectors.dense.DenseVector;
import util.constants.enums.RegularizationFunction;

import static statistics.util.Statistics.meanSquaredError;

public class Functions {

    public static MarkedNode lossFunction(DenseVector denseVector1, DenseVector denseVector2, RegularizationFunction regularizationFunction, double regularizationCoefficient, int varPos) {
        MarkedNode loss = meanSquaredError(denseVector1, denseVector2, varPos);
        if (regularizationFunction.equals(RegularizationFunction.L1)) {
            double cons = 0;
            cons += regularizationCoefficient * (denseVector2.slice(0, denseVector2.size()).map(Math::abs).reduce(0.0,Double::sum) - Math.abs(denseVector2.value(varPos)));
            MarkedNode innerNode = new MarkedNode(1, cons);
            loss.setChildNode(innerNode);
            loss.setChildFunctionalRelation(AlgebraicFunction.ADD);
        } else if (regularizationFunction.equals(RegularizationFunction.L2)) {
            double cons = 0;
            cons += (0.5 * regularizationCoefficient) *
                    (((denseVector2.slice(0, denseVector2.size()).map(x -> Math.pow(x, 2)))
                            .reduce(0.0,Double::sum) - Math.pow(denseVector2.value(varPos), 2.0)) / denseVector2.size() - 1);
            MarkedNode innerNode = new MarkedNode(1, cons);
            loss.setChildNode(innerNode);
            loss.setChildFunctionalRelation(AlgebraicFunction.ADD);
            loss.setChildNodeMultiplicand(denseVector2.size() - 1);
        }
        return loss;
    }


}
