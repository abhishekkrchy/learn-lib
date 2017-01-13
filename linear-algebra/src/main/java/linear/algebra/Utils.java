package linear.algebra;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.util.ExceptionUtils;
import linear.algebra.util.constants.exception.ExceptionConstants;
import linear.algebra.vectors.dense.DenseVector;
public class Utils {
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
}
