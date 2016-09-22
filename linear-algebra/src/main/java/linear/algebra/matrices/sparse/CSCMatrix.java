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

    public CSCMatrix() {
        this(0, 0, new double[]{}, new int[]{}, new int[]{});
    }

    public CSCMatrix(int rows, int columns, double[] values, int[] rowNums, int[] columnPointers) {
        super(rows, columns);
        this.values = values;
        this.rowNums = rowNums;
        this.columnPointers = columnPointers;
    }

    @Override
    public double value(int row, int column) {
        int startIndexInColumn = columnPointers[column];
        int endIndexInColumn = column == columnPointers.length - 1 ? values.length - 1 : columnPointers[column + 1] - 1;
        for (int i = startIndexInColumn; i <= endIndexInColumn; i++) {
            int rowGot = rowNums[i];
            if (rowGot == row) {
                return values[i];
            }
        }
        return 0;
    }

}
