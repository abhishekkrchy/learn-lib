package linear.algebra;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.ExceptionUtils;
import linear.algebra.util.Polynomial;
import linear.algebra.util.constants.enums.AlgebraicFunction;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.util.constants.exception.ExceptionConstants;
import linear.algebra.util.poly.Pair;
import linear.algebra.util.poly.SingleVarPolynomial;
import linear.algebra.vectors.dense.DenseVector;

//TODO:: clean this class and use generics.
public class Utils {

//    public static DenseVector multiplyWithVar(DenseVector yVector, DenseMatrix denseMatrix, DenseVector denseVector, int varPos) throws Exception {
//
//        if (denseMatrix.getColumns() != denseVector.size())
//            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
//
//        Polynomial result = new Polynomial(1.0, 0.0);
//        for (int i = 0; i < denseVector.size(); i++) {
////
////            Errors.MARKED_ERROR_FUNCTION.apply(ErrorType.MSE).apply(varPos).apply(yVector,
//
//            double constantSubtractedFromY = yVector.value(i) - dotProduct(denseMatrix.getRow(i), denseVector) - (denseMatrix.value(i, varPos) * denseVector.value(i));
//
//
//
//            Polynomial xValue = new Polynomial(1.0,denseMatrix.value(i, varPos));
//            Polynomial temp2 = new Polynomial(2.0,cst, AlgebraicFunction.SUB, temp);
//            result = new Polynomial(2.0,1, AlgebraicFunction.SUB, temp);
//
//
//        }
//        return result;
//
//        double[] result = new double[denseVector.size()];
//        for (int i = 0; i < denseVector.size(); i++) {
//            result[i] = dotProduct(denseMatrix.getRow(i), denseVector);
//        }
//        return new DenseVector(result);
//    }

    public static SingleVarPolynomial[] multiply(DenseMatrix denseMatrix, DenseVector denseVector, int varPos) throws Exception {
        if (denseMatrix.getColumns() != denseVector.size())
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
        SingleVarPolynomial[] result = new SingleVarPolynomial[denseVector.size()-1];
        for (int i = 0; i < denseVector.size()-1; i++) {
            result[i] = dotProductWithVar(denseMatrix.getRow(i), denseVector, varPos);
        }
        return result;
    }

    public static DenseVector multiply(DenseMatrix denseMatrix, DenseVector denseVector) throws Exception {
        if (denseMatrix.getColumns() != denseVector.size())
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
        double[] result = new double[denseVector.size()];
        for (int i = 0; i < denseVector.size(); i++) {
            result[i] = dotProduct(denseMatrix.getRow(i), denseVector);
        }
        return new DenseVector(result);
    }

    public static DenseVector multiply(DenseVector denseVector, DenseMatrix denseMatrix) throws Exception {
        if (denseMatrix.getRows() != denseVector.size())
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
        double[] result = new double[denseVector.size()];
        for (int i = 0; i < denseVector.size(); i++) {
            result[i] = dotProduct(denseMatrix.getColumn(i), denseVector);
        }
        return new DenseVector(result);
    }

    public static double dotProduct(DenseVector denseVector1, DenseVector denseVector2) throws Exception {
        if (denseVector1.size() != denseVector2.size()) {
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
        } else {
            double result = 0.0;
            for (int i = 0; i < denseVector1.size(); i++) {
                result += (denseVector1.value(i) * denseVector2.value(i));
            }
            return result;
        }
    }

    public static SingleVarPolynomial dotProductWithVar(DenseVector denseVector1, DenseVector denseVector2, int varPos) throws Exception {
        if (denseVector1.size() != denseVector2.size()) {
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
        } else {
            double total = 0.0;
            SingleVarPolynomial singleVarPolynomial = new SingleVarPolynomial(1);

            for (int i = 0; i < denseVector1.size(); i++) {

                if (i == varPos) {
                    singleVarPolynomial.term(denseVector1.value(i), 1);
                }else{
                    total += (denseVector1.value(i) * denseVector2.value(i));
                }
            }
            return singleVarPolynomial.term(total);
        }
    }
}
