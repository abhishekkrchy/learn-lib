package linear.algebra.matrices.utils;

import linear.algebra.Matrix;
import linear.algebra.matrices.dense.TwoDimensionalMatrix;
import linear.algebra.matrices.sparse.CRSMatrix;
import linear.algebra.matrices.sparse.CSCMatrix;
import utils.ExceptionConstants;
import utils.ExceptionUtils;

/**
 * Created by abhishek on 1/10/16.
 * <p>
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
    public static Matrix multiply(TwoDimensionalMatrix matrix1, TwoDimensionalMatrix matrix2) throws Exception {
        checkCompatibility(matrix1.getColumns(), matrix2.getRows());
        double[][] productMatrixValues = new double[matrix1.getRows()][matrix2.getColumns()];
        for (int i = 0; i < matrix1.getRows(); i++) {
            for (int j = 0; j < matrix2.getColumns(); j++) {
                for (int k = 0; k < matrix1.getRows(); k++) {
                    for (int l = 0; l < matrix1.getColumns(); l++) {
                        productMatrixValues[i][j] += matrix1.value(k, l) * matrix2.value(l, k);
                    }
                }
            }
        }
        return new TwoDimensionalMatrix(productMatrixValues);
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
