package optimizer.functions;

import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.MarkedNode;
import linear.algebra.util.constants.enums.AlgebraicFunction;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import util.constants.enums.Regularizer;

public class Functions {
    public static MarkedNode lossFunction(DenseVector denseVector1, DenseVector denseVector2, Regularizer regularizer, double regularizationCoefficient, ErrorType errorType, int varPos) {
        MarkedNode loss = Errors.MARKED_ERROR_FUNCTION.apply(errorType)
                .apply(varPos).apply(denseVector1, denseVector2);
        MarkedNode innerNode = Regularizers.regularize(denseVector2, regularizer, regularizationCoefficient, varPos);
        loss.setChildNode(innerNode);
        loss.setChildFunctionalRelation(AlgebraicFunction.ADD);
        if (regularizer.equals(Regularizer.L1)) {
            loss.setChildNodeMultiplicand(denseVector2.size() - 1);
        }
        return loss;
    }


}
