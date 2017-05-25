package linear.algebra.matrices.dense;

import linear.algebra.Matrix;
import linear.algebra.vectors.dense.DenseVector;

import java.util.Arrays;

public class DenseMatrix extends Matrix {
    private double[][] values;

    /**
     * Instantiates a new Two dimensional matrix.
     */
    public DenseMatrix() {
        this(0, 0);
    }

    /**
     * Instantiates a new Two dimensional matrix.
     *
     * @param rows    the rows
     * @param columns the columns
     */
    public DenseMatrix(int rows, int columns) {
        super(rows, columns);
        this.values = new double[rows][columns];
    }

    /**
     * Instantiates a new Two dimensional matrix.
     *
     * @param values the values
     */
    public DenseMatrix(double[][] values) {
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
        return new DenseMatrix(transposed);
    }

    public DenseVector getRow(int index) {
        return new DenseVector(values[index]);
    }

    public DenseVector getColumn(int index) {
        double[] column = new double[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = value(index,i);
        }
        return new DenseVector(column);
    }

}
