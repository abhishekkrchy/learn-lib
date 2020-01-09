package linear.algebra.matrices.sparse;

import linear.algebra.expressions.Polynomial;
import linear.algebra.matrices.Matrix;
import linear.algebra.vectors.Vector;

/**
 * This class is the representation for a
 * CSC(Compressed Sparse Column) matrix and
 * some related operations.
 */
public class CSCMatrix extends Matrix {
    private double[] values;
    private int[] rowNums;
    private int[] columnPointers;

    /**
     * Instantiates a new CSC matrix.
     */
    public CSCMatrix() {
        this(0, 0, new double[]{}, new int[]{}, new int[]{});
    }


    /**
     * Instantiates a new CSC matrix.
     *
     * @param rows           the number of rows
     * @param columns        the number of columns
     * @param values         the value array(non-zero values in the sparse matrix)
     * @param rowNums        the row nums or row-indexes of the non zero elements
     * @param columnPointers the column pointers index of 1st non-zero element in ith column
     */
    public CSCMatrix(int rows, int columns, double[] values, int[] rowNums, int[] columnPointers) {
        super(rows, columns);
        this.values = values;
        this.rowNums = rowNums;
        this.columnPointers = columnPointers;
    }


    @Override
    public double value(int row, int column) {
        int startIndexInCol = columnPointers[column];
        int endIndexInCol = columnPointers[column + 1] - 1;
        for (int i = startIndexInCol; i <= endIndexInCol; i++) {
            int rowGot = rowNums[i];
            if (rowGot == row) {
                return values[i];
            }
        }
        return 0;
    }

    @Override
    public void setValue(int row, int column, double value) {
        // TODO
    }

    @Override
    public void setRow(int row, Vector rowContents) {
        // TODO
    }


    @Override
    public Matrix transpose() {
        return new CRSMatrix(rows, columns, values, rowNums, columnPointers);
    }

    @Override
    public Vector getRow(int index) {
        return null;
    }

    @Override
    public Vector getColumn(int index) {
        return null;
    }

    @Override
    public Matrix addColumn(double values) {
        return null;
    }

    @Override
    public Vector multiply(Vector vector) {
        return null;
    }

    @Override
    public Vector multiplyAndAddIntercept(Vector vector) {
        return null;
    }

    @Override
    public Polynomial[] multiplyWithVariable(Vector vector, int varPos) {
        return new Polynomial[0];
    }

}
