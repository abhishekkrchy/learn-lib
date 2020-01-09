package linear.algebra.statistics.errors;

import linear.algebra.expressions.Polynomial;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * The Errors class
 * for method related to
 * calculating statistical
 * errors.
 */
public class Errors {

    private Errors() {

    }

    /**
     * The function ERROR_FUNCTION
     * which when applied with an arguement
     * of type {@link ErrorType} returns
     * a {@link BiFunction} which takes
     * two {@link DenseVector}s and when applied
     * returns a {@link Double} which is the
     * calculated error.
     */
    public static BiFunction<DenseVector, DenseVector, Double> type(ErrorType errorType) {
        switch (errorType) {
            case MSE:
            default:
                return MeanSquaredError::error;
        }
    }


    public static BiFunction<DenseVector, DenseVector, Polynomial> markedType(ErrorType errorType, int varIndex) {
        return MARKED_ERROR_FUNCTION.apply(errorType).apply(varIndex);
    }

    /**
     * The function MARKED_ERROR_FUNCTION
     * which when applied with an arguement
     * of type {@link ErrorType} returns
     * a {@link Function} to which if an
     * {@link Integer} (which is the variable index)
     * is passed as arguement produces a {@link BiFunction}
     * which takes two {@link DenseVector}s and when
     * applied returns a {@link Polynomial} which is the
     * calculated error.
     */
    private static final Function<ErrorType, Function<Integer, BiFunction<DenseVector, DenseVector, Polynomial>>> MARKED_ERROR_FUNCTION = errorType -> varIndex -> (BiFunction<DenseVector, DenseVector, Polynomial>) (denseVector, denseVector2) -> {
        switch (errorType) {
            case MSE:
            default:
                return MeanSquaredError.error(denseVector, denseVector2, varIndex);
        }
    };


}
