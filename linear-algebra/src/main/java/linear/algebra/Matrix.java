package linear.algebra;

/**
 * Created by abhishek on 20/9/16.
 */
public abstract class Matrix {
    protected int rows;
    protected int columns;

    public Matrix() {
        this(0, 0);
    }


    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public abstract double value(int row, int column);

    public double trace() {
        double result = 0.0;
        for (int i = 0; i < rows; i++) {
            result += value(i, i);
        }
        return result;
    }

}
