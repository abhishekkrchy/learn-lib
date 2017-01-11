package linear.algebra.vectors.dense;

import linear.algebra.Vector;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.stream;


/**
 * A simple dense vector class.
 */
public class DenseVector extends Vector {
    private double[] values;


    /**
     * Instantiates a new Dense vector.
     * TODO::
     * @param values the size of the vector.
     */
    public DenseVector(double[] values) {
        super(values.length);
        this.values = values;
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

    public DenseVector map(DoubleUnaryOperator function) {
        return new DenseVector(stream(this.values).map(function).toArray());
    }

    public double reduce(DoubleBinaryOperator function) {
        return stream(this.values).reduce(0, function);
    }

    public DenseVector slice(int fromIndex, int toIndex) {
        return new DenseVector(copyOfRange(this.values, fromIndex, toIndex));
    }

}
