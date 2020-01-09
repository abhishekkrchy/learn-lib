package linear.algebra.vectors.dense;

import linear.algebra.expressions.Polynomial;
import linear.algebra.util.ExceptionUtils;
import linear.algebra.util.constants.exception.ExceptionConstants;
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
    public double dotProduct(Vector other) {
        checkCompatibility(other);
        double result = 0.0;
        for (int i = 0; i < size(); i++) {
            result += (value(i) * other.value(i));
        }
        return result;
    }

    @Override
    public Polynomial dotProductWithVariable(Vector other, int varPos) {
        checkCompatibility(other);
        double total = 0.0;
        Polynomial polynomial = new Polynomial(1);
        for (int i = 0; i < size(); i++) {
            if (i == varPos) {
                polynomial.term(value(i), 1);
            } else {
                total += (value(i) * other.value(i));
            }
        }
        return polynomial.term(total);
    }

    @Override
    public double head() {
        return value(0);
    }

    @Override
    public DenseVector tail() {
        return slice(1, size());
    }

    private void checkCompatibility(Vector other) {
        if (size() != other.size()) {
            throw ExceptionUtils.getException(ExceptionConstants.INCOMPATIBLE_MATRICES);
        }
    }

    @Override
    public DoubleStream stream() {
        return Arrays.stream(values);
    }
}
