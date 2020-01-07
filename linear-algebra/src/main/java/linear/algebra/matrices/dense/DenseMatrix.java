package linear.algebra.matrices.dense;

import linear.algebra.matrices.Matrix;
import linear.algebra.vectors.Vector;
import linear.algebra.vectors.dense.DenseVector;

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
    public void setValue(int row, int column, double value) {
        values[row][column]=value;
    }

    @Override
    public void setRow(int row, Vector rowContents) {
        for (int i = 0; i < rowContents.size(); i++) {
            values[row][i] = rowContents.value(i);
        }
    }

    @Override
    public DenseMatrix transpose() {
        double[][] transposed = new double[values[0].length][values.length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                transposed[i][j] = values[j][i];
            }
        }
        return new DenseMatrix(transposed);
    }

    @Override
    public DenseVector getRow(int index) {
        return new DenseVector(values[index]);
    }

    @Override
    public DenseVector getColumn(int index) {
        double[] column = new double[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = value(i, index);
        }
        return new DenseVector(column);
    }

}
