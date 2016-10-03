package linear.algebra.matrices.sparse;

import linear.algebra.Matrix;

/**
 * Created by abhishek on 4/10/16.
 * <p>
 * This class is the representation for a
 * CSC(Compressed Sparse Column) matrix and
 * some related operations.
 * </p>
 */
public class CRSMatrix extends Matrix {
    private double[] values;
    private int[] columnNums;
    private int[] rowPointers;

    /**
     * Instantiates a new CRS matrix.
     */
    public CRSMatrix() {
        this(0, 0, new double[]{}, new int[]{}, new int[]{});
    }


    /**
     * Instantiates a new CRS matrix.
     *
     * @param rows        the number of rows
     * @param columns     the number of columns
     * @param values      the value array(non-zero values in the sparse matrix)
     * @param columnNums  the col nums or col-indexes of the non zero elements
     * @param rowPointers the row pointers index of 1st non-zero element in ith row
     */
    public CRSMatrix(int rows, int columns, double[] values, int[] columnNums, int[] rowPointers) {
        super(rows, columns);
        this.values = values;
        this.columnNums = columnNums;
        this.rowPointers = rowPointers;
    }


    @Override
    public double value(int row, int column) {
        int startIndexInRow = rowPointers[row];
        int endIndexInRow = rowPointers[row + 1] - 1;
        for (int i = startIndexInRow; i <= endIndexInRow; i++) {
            int columnGot = columnNums[i];
            if (columnGot == column) {
                return values[i];
            }
        }
        return 0;
    }


    @Override
    public Matrix transpose() {
        return new CSCMatrix(rows, columns, values, columnNums, rowPointers);
    }
}
