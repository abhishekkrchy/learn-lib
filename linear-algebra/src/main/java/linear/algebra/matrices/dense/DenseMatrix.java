package linear.algebra.matrices.dense;

import linear.algebra.expressions.Polynomial;
import linear.algebra.matrices.Matrix;
import linear.algebra.util.ExceptionUtils;
import linear.algebra.util.constants.exception.ExceptionConstants;
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
        values[row][column] = value;
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

    @Override
    public Matrix addColumn(double defaultValue) {
        double[][] doubles = new double[getRows()][getColumns() + 1];
        for (int i = 0; i < getRows(); i++) {
            doubles[i][0] = defaultValue;
            for (int j = 0; j < getColumns(); j++) {
                doubles[i][j + 1] = value(i, j);
            }
        }
        return new DenseMatrix(doubles);
    }

    public DenseVector multiply(Vector vector) {
        checkCompatibility(vector);
        double[] result = new double[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            result[i] = getRow(i).dotProduct(vector);
        }
        return new DenseVector(result);
    }

    public Polynomial[] multiplyWithVariable(Vector vector, int varPos) {
        Polynomial[] result = new Polynomial[vector.size() - 1];
        for (int i = 0; i < vector.size() - 1; i++) {
            result[i] = getRow(i).dotProductWithVariable(vector, varPos);
        }
        return result;
    }

    private void checkCompatibility(Vector vector) {
        if (getColumns() != vector.size()) {
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
        }
    }
}
