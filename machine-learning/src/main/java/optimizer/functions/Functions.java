package optimizer.functions;

import linear.algebra.expressions.Polynomial;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import util.Data;
import util.constants.enums.Regularizer;

import java.util.Arrays;

/**
 * The type Functions.
 */
public class Functions {

    private Functions() {

    }

    /**
     * Loss function which calculates the loss based
     * on error and regularization.
     *
     * @param training                  the dense matrix training
     * @param factors                   the dense vector of coefficients/factors of variables
     * @param regularizer               the regularizer
     * @param regularizationCoefficient the regularization coefficient
     * @param errorType                 the error type
     * @param varIndex                  the variable index
     * @return the marked node
     */
    public static Polynomial markedLossFunction(Data data, DenseVector factors, Regularizer regularizer, double regularizationCoefficient, ErrorType errorType, int varIndex) {
        Polynomial[] estimatedValue = data.trainingX().addColumn(0, 1).multiplyWithVariable(factors, varIndex);
        if (errorType == ErrorType.MSE) {
            for (int i = 0; i < data.trainingY().size(); i++) {
                estimatedValue[i].term(-1 * data.trainYVal(i));
            }
            return Arrays.stream(estimatedValue)
                    .map(Polynomial::squared)
                    .map(x -> x.divideCoefficients(estimatedValue.length))
                    .reduce(new Polynomial(2), Polynomial::add)
                    .term(Regularizers.regularize(factors, regularizer, regularizationCoefficient));
        }
        throw new IllegalArgumentException("Unknown error type");
    }
}
