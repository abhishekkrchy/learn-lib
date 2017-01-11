package linear.algebra;

import linear.algebra.matrices.dense.TwoDimensionalMatrix;
import linear.algebra.vectors.dense.DenseVector;
import utils.ExceptionConstants;
import utils.ExceptionUtils;

public class Utils {
    public static DenseVector multiply(TwoDimensionalMatrix twoDimensionalMatrix, DenseVector denseVector) throws Exception {
        if (twoDimensionalMatrix.getColumns() != denseVector.size)
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
        double[] result = new double[denseVector.size];
        for (int i = 0; i < denseVector.size; i++) {
            result[i] = dotProduct(twoDimensionalMatrix.getRow(i), denseVector);
        }
        return new DenseVector(result);
    }

    public static DenseVector multiply(DenseVector denseVector, TwoDimensionalMatrix twoDimensionalMatrix) throws Exception {
        if (twoDimensionalMatrix.getRows() != denseVector.size)
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
        double[] result = new double[denseVector.size];
        for (int i = 0; i < denseVector.size; i++) {
            result[i] = dotProduct(twoDimensionalMatrix.getColumn(i), denseVector);
        }
        return new DenseVector(result);
    }

    public static double dotProduct(DenseVector denseVector1, DenseVector denseVector2) throws Exception {
        if (denseVector1.size != denseVector2.size) {
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
        } else {
            double result = 0.0;
            for (int i = 0; i < denseVector1.size; i++) {
                result += (denseVector1.value(i) * denseVector2.value(i));
            }
            return result;
        }
    }
}
