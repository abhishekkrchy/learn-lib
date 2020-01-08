package linear.algebra.matrices;

import linear.algebra.vectors.Vector;

public abstract class Matrix {

    private static final String DEFAULT_INDENT_STRING = "  ";
    /**
     * Number of Rows In The Matrix.
     */
    protected int rows;
    /**
     * Number of Columns In The Matrix.
     */
    protected int columns;

    /**
     * Instantiates a new Matrix.
     */
    public Matrix() {
        this(0, 0);
    }


    /**
     * Instantiates a new Matrix.
     *
     * @param rows    sets the number of rows
     * @param columns sets the number of columns
     */
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Finds the value stored at index (row,column) of the matrix
     *
     * @param row    the row index
     * @param column the column index
     * @return the double value stored
     */
    public abstract double value(int row, int column);

    public abstract void setValue(int row, int column, double value);

    public abstract void setRow(int row, Vector rowContents);

    /**
     * Gets number of rows.
     *
     * @return the rows' count
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets number of columns.
     *
     * @return the columns' count
     */
    public int getColumns() {
        return columns;
    }


    /**
     * Trace of a given matrix.
     *
     * @return the trace
     */
    public double trace() {
        double result = 0.0;
        for (int i = 0; i < rows; i++) {
            result += value(i, i);
        }
        return result;
    }

    /**
     * Transposes this matrix and returns the transposed matrix.
     *
     * @return the transposed matrix
     */
    public abstract Matrix transpose();

    public abstract Vector getRow(int index);

    public abstract Vector getColumn(int index);

    @Override
    public String toString() {
        StringBuilder matrixString = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixString.append(value(i, j)).append(DEFAULT_INDENT_STRING);
            }
            matrixString.append("\n");
        }
        return matrixString.toString();
    }
}
