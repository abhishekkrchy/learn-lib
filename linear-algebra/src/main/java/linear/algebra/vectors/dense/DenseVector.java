package linear.algebra.vectors.dense;

import linear.algebra.Vector;


/**
 * Created by abhishek on 4/10/16.
 * <p>
 * A simple dense vector class.
 */
public class DenseVector extends Vector {
    private double[] values;


    /**
     * Instantiates a new Dense vector.
     *
     * @param size the size of the vector.
     */
    public DenseVector(int size) {
        super(size);
        this.values = new double[size];
    }

    public double value(int index) {
        return values[index];
    }


    /**
     * Instantiates a new Dense vector.
     *
     * @param size   the size of the vector.
     * @param values the values of vector.
     */
    public DenseVector(int size, double[] values) {
        super(size);
        this.values = values;
    }

}
