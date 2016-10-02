package linear.algebra.matrices.dense;

import linear.algebra.Matrix;
import linear.algebra.matrices.utils.Matrices;

/**
 * Created by abhishek on 20/9/16.
 */
public class TwoDimensionalMatrix extends Matrix {
    private double[][] values;

    /**
     * Instantiates a new Two dimensional matrix.
     */
    public TwoDimensionalMatrix() {
        this(0, 0);
    }

    /**
     * Instantiates a new Two dimensional matrix.
     *
     * @param rows    the rows
     * @param columns the columns
     */
    public TwoDimensionalMatrix(int rows, int columns) {
        super(rows, columns);
        this.values = new double[rows][columns];
    }

    /**
     * Instantiates a new Two dimensional matrix.
     *
     * @param values the values
     */
    public TwoDimensionalMatrix(double[][] values) {
        super(values.length, values[0].length);
        this.values = values;
    }

    @Override
    public double value(int row, int column) {
        return values[row][column];
    }

    @Override
    public Matrix transpose() {
        double[][] transposed = new double[values[0].length][values.length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                transposed[i][j] = values[j][i];
            }
        }
        return new TwoDimensionalMatrix(transposed);
    }


}
