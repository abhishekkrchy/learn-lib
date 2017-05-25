package linear.algebra.vectors.dense;

import linear.algebra.Vector;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.DoubleStream;

import static java.util.Arrays.copyOfRange;

/**
 * A simple dense vector class.
 * This class in abstraction for
 * a double array supporting
 * various useful methods.
 */
public class DenseVector implements Vector {
    private double[] values;

    /**
     * Instantiates a new Dense vector.
     *
     * @param values the values(array of doubles)
     *               contained within the class
     */
    public DenseVector(double[] values) {
        this.values = values;
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public double value(int index) {
        return values[index];
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator<Double>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index > size();
            }

            @Override
            public Double next() {
                return value(index++);
            }
        };
    }

    @Override
    public DenseVector slice(int fromIndex, int toIndex) {
        return new DenseVector(copyOfRange(this.values, fromIndex, toIndex));
    }

    @Override
    public DoubleStream stream() {
        return Arrays.stream(values);
    }

}
