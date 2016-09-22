package linear.algebra.matrices.dense;

import linear.algebra.Matrix;

/**
 * Created by abhishek on 20/9/16.
 */
public class TwoDimensionalMatrix extends Matrix {
    private double[][] values;

    public TwoDimensionalMatrix() {
        this(0, 0);
    }

    public TwoDimensionalMatrix(int rows, int columns) {
        super(rows, columns);
        this.values = new double[rows][columns];
    }

    public TwoDimensionalMatrix(double[][] values) {
        super(values.length, values[0].length);
        this.values = values;
    }

    @Override
    public double value(int row, int column) {
        return values[row][column];
    }


}
