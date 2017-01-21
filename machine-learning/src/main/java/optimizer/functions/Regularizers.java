package optimizer.functions;

import linear.algebra.util.MarkedNode;
import linear.algebra.vectors.dense.DenseVector;
import util.constants.enums.Regularizer;

public class Regularizers {
    public static MarkedNode regularize(DenseVector denseVector2, Regularizer regularizer, double regularizationCoefficient, int varPos) {
        double constant = 0;
        switch (regularizer) {
            case L1:
                constant += regularizationCoefficient * (denseVector2.slice(0, denseVector2.size()).stream().map(Math::abs).reduce(0.0, Double::sum) - Math.abs(denseVector2.value(varPos)));
                break;
            case L2:
                constant += ((0.5 * regularizationCoefficient) *
                        (((denseVector2.slice(0, denseVector2.size()).stream().map(x -> Math.pow(x, 2)))
                                .reduce(0.0, Double::sum) - Math.pow(denseVector2.value(varPos), 2.0)) / denseVector2.size() - 1));
                break;
            default:
                break;

        }
        return new MarkedNode(1, constant);
    }
}
