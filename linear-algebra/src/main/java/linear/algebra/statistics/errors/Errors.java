package linear.algebra.statistics.errors;

import linear.algebra.util.MarkedNode;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Errors {
    public static Function<ErrorType, BiFunction<DenseVector, DenseVector, Double>> ERROR_FUNCTION = errorType -> (denseVector, denseVector2) -> {
        switch (errorType) {
            case MSE:
            default:
                return MeanSquaredError.error(denseVector, denseVector2);
        }
    };

    public static Function<ErrorType, Function<Integer, BiFunction<DenseVector, DenseVector, MarkedNode>>> MARKED_ERROR_FUNCTION = new Function<ErrorType, Function<Integer, BiFunction<DenseVector, DenseVector, MarkedNode>>>() {
        @Override
        public Function<Integer, BiFunction<DenseVector, DenseVector, MarkedNode>> apply(ErrorType errorType) {
            return integer -> (BiFunction<DenseVector, DenseVector, MarkedNode>) (denseVector, denseVector2) -> {
                switch (errorType) {
                    case MSE:
                    default:
                        return MeanSquaredError.error(denseVector, denseVector2, integer);
                }
            };
        }
    };


}
