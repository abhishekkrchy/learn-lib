package linear.algebra;

/**
 * Created by abhishek on 20/9/16.
 */
public abstract class Matrix {
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
     * @param rows    the rows
     * @param columns the columns
     */
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Value double.
     *
     * @param row    the row
     * @param column the column
     * @return the double
     */
    public abstract double value(int row, int column);

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
     * Transpose matrix.
     *
     * @return the matrix
     */
    public abstract Matrix transpose();
}
