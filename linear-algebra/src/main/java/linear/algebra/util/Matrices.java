package linear.algebra.util;

import linear.algebra.matrices.Matrix;
import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.matrices.sparse.CRSMatrix;
import linear.algebra.matrices.sparse.CSCMatrix;
import linear.algebra.util.constants.exception.ExceptionConstants;

/**
 * Utility class for matrix operations.
 */
public class Matrices {

    /**
     * Multiply matrix.
     *
     * @param matrix1 LHS matrix of dimensions m x n
     * @param matrix2 RHS matrix of dimensions n x p
     * @return the product matrix of dimensions m x p
     * @throws Exception dimensions incompatible exception
     */
    public static Matrix multiply(DenseMatrix matrix1, DenseMatrix matrix2) throws Exception {
        checkCompatibility(matrix1.numColumns(), matrix2.numRows());
        double[][] productMatrixValues = new double[matrix1.numRows()][matrix2.numColumns()];
        for (int i = 0; i < matrix1.numRows(); i++) {
            for (int j = 0; j < matrix2.numColumns(); j++) {
                for (int k = 0; k < matrix1.numRows(); k++) {
                    for (int l = 0; l < matrix1.numColumns(); l++) {
                        productMatrixValues[i][j] += matrix1.value(k, l) * matrix2.value(l, k);
                    }
                }
            }
        }
        return new DenseMatrix(productMatrixValues);
    }

    //TODO add
    public static Matrix multiply(CSCMatrix cscMatrix1, CSCMatrix cscMatrix2) {
        return null;
    }

    //TODO add
    public static Matrix multiply(CRSMatrix crsMatrix1, CRSMatrix crsMatrix2) {
        return null;
    }


    private static void checkCompatibility(int columnsOfFirstMatrix, int rowsOfSecondMatrix) throws Exception {
        boolean compatible = columnsOfFirstMatrix == rowsOfSecondMatrix;
        if (!compatible)
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
    }
}
