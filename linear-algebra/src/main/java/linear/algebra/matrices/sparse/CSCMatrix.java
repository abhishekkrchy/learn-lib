package linear.algebra.matrices.sparse;

import linear.algebra.Matrix;

import java.util.Arrays;

/**
 * Created by abhishek on 20/9/16.
 */
public class CSCMatrix extends Matrix {
    private double[] values;
    private int[] rowNums;
    private int[] columnPointers;

    /**
     * Instantiates a new Csc matrix.
     */
    public CSCMatrix() {
        this(0, 0, new double[]{}, new int[]{}, new int[]{});
    }


    /**
     * Instantiates a new Csc matrix.
     *
     * @param rows           the rows
     * @param columns        the columns
     * @param values         the values
     * @param rowNums        the row nums
     * @param columnPointers the column pointers
     */
    public CSCMatrix(int rows, int columns, double[] values, int[] rowNums, int[] columnPointers) {
        super(rows, columns);
        this.values = values;
        this.rowNums = rowNums;
        this.columnPointers = columnPointers;
    }

    @Override
    public double value(int row, int column) {
        int startIndexInRow = rowNums[row];
        int endIndexInRow = row == rowNums.length - 1 ? values.length - 1 : rowNums[row + 1] - 1;
        for (int i = startIndexInRow; i <= endIndexInRow; i++) {
            int columnGot = columnPointers[i];
            if (columnGot == column) {
                return values[i];
            }
        }
        return 0;
    }


    @Override
    public Matrix transpose() {
        return null;
    }

}
