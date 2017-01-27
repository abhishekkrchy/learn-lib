package optimizer.functions;

import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.MarkedNode;
import linear.algebra.util.constants.enums.AlgebraicFunction;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import util.constants.enums.Regularizer;

/**
 * The type Functions.
 */
public class Functions {
    /**
     * Loss function which calculates the loss based
     * on error and regularization.
     *
     * @param denseVector1              the dense vector 1
     * @param denseVector2              the dense vector 2
     * @param regularizer               the regularizer
     * @param regularizationCoefficient the regularization coefficient
     * @param errorType                 the error type
     * @param varPos                    the variable pos
     * @return the marked node
     */
//TODO :: change varPos for features when done with tests
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
