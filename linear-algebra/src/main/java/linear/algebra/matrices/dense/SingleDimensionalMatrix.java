package linear.algebra.matrices.dense;

import linear.algebra.Matrix;

/**
 * Created by abhishek on 20/9/16.
 */
public class SingleDimensionalMatrix extends Matrix {

    private double[] values;

    /**
     * Instantiates a new Single dimensional matrix.
     */
    public SingleDimensionalMatrix() {
        this(0, 0);
    }

    /**
     * Instantiates a new Single dimensional matrix.
     *
     * @param rows    the rows
     * @param columns the columns
     */
    public SingleDimensionalMatrix(int rows, int columns) {
        super(rows, columns);
        this.values = new double[this.rows * this.columns];
    }

    /**
     * Instantiates a new Single dimensional matrix.
     *
     * @param rows    the rows
     * @param columns the columns
     * @param values  the values
     */
    public SingleDimensionalMatrix(int rows, int columns, double[] values) {
        super(rows, columns);
        this.values = values;
    }

    @Override
    public double value(int row, int column) {
        return values[row * columns + column];
    }

    @Override
    public Matrix transpose() {
        return new SingleDimensionalMatrix(this.columns,this.rows,values);
    }


}
