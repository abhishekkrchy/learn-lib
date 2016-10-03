package linear.algebra.matrices.sparse;

import linear.algebra.Matrix;

/**
 * Created by abhishek on 4/10/16.
 */
public class CRSMatrix extends Matrix {
    private double[] values;
    private int[] columnNums;
    private int[] rowPointers;

    /**
     * Instantiates a new Csc matrix.
     */
    public CRSMatrix() {
        this(0, 0, new double[]{}, new int[]{}, new int[]{});
    }


    /**
     * Instantiates a new Csc matrix.
     *
     * @param rows           the rows
     * @param columns        the columns
     * @param values         the values
     * @param columnNums        the row nums
     * @param rowPointers <>     </>he column pointers
     */
    public CRSMatrix(int rows, int columns, double[] values, int[] columnNums, int[] rowPointers) {
        super(rows, columns);
        this.values = values;
        this.columnNums = columnNums;
        this.rowPointers = rowPointers;
    }


    @Override
    public double value(int row, int column) {
        int startIndexInColumn = columnNums[row];
        int endIndexInColumn = column == columnNums.length - 1 ? values.length - 1 : columnNums[column + 1] - 1;
        for (int i = startIndexInColumn; i <= endIndexInColumn; i++) {
            int rowGot = rowPointers[i];
            if (rowGot == row) {
                return values[i];
            }
        }
        return 0;
    }


    @Override
    public Matrix transpose() {
        return new CSCMatrix(rows,columns,values,columnNums,rowPointers);
    }
}
