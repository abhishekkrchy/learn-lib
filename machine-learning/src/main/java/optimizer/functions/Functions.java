package optimizer.functions;

import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.Polynomial;
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
    public static Polynomial lossFunction(DenseVector denseVector1, DenseVector denseVector2, Regularizer regularizer, double regularizationCoefficient, ErrorType errorType, int varPos) {
        Polynomial loss = Errors.MARKED_ERROR_FUNCTION.apply(errorType)
                .apply(varPos).apply(denseVector1, denseVector2);
        double multiplier = Regularizers.regularize(denseVector2, regularizer, regularizationCoefficient, varPos);
        if (regularizer.equals(Regularizer.L1)) {
            multiplier = multiplier * (denseVector2.size() - 1);
        }

        return new Polynomial(1.0, multiplier, AlgebraicFunction.ADD, loss);
    }
}
