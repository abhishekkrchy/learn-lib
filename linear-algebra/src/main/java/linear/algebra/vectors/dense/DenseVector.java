package linear.algebra.vectors.dense;

import linear.algebra.vectors.Vector;

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
public class DenseVector extends Vector {
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

    public DenseVector(int size) {
        this.values = new double[size];
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
    public void setValue(int index, double value) {
        values[index] = value;
    }

    @Override
    public DenseVector allExcept(int index) {
        double[] doubles = new double[values.length - 1];
        int ri = 0;
        for (int i = 0; i < values.length; i++) {
            if (i != index) {
                doubles[ri++] = values[i];
            }
        }
        return new DenseVector(doubles);
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
