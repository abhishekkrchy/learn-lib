package linear.algebra.matrices.dense;

import linear.algebra.Matrix;

/**
 * Created by abhishek on 20/9/16.
 */
public class SingleDimensionalMatrix extends Matrix {
    private double[] values;

    public SingleDimensionalMatrix() {
        this(0, 0);
    }

    public SingleDimensionalMatrix(int rows, int columns) {
        super(rows, columns);
        this.values = new double[rows * columns];
    }

    public SingleDimensionalMatrix(int rows, int columns, double[] values) {
        super(rows, columns);
        this.values = values;
    }

    @Override
    public double value(int row, int column) {
        return values[row * columns + column];
    }


}
