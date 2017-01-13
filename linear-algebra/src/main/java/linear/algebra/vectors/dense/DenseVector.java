package linear.algebra.vectors.dense;

import linear.algebra.Vector;

import java.util.Iterator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleUnaryOperator;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.stream;


/**
 * A simple dense vector class.
 */
public class DenseVector implements Vector {
    private double[] values;

    public DenseVector(double[] values) {
        this.values = values;
    }

    @Override
    public int size() {
        return values.length;
    }

    public double value(int index) {
        return values[index];
    }

    @Override
    public void forEach(DoubleConsumer action) {
        stream(values).forEach(action);
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
    public DenseVector map(DoubleUnaryOperator function) {
        return new DenseVector(stream(this.values).map(function).toArray());
    }

    @Override
    public double reduce(double identity,DoubleBinaryOperator function) {
        return stream(this.values).reduce(identity, function);
    }

    public DenseVector slice(int fromIndex, int toIndex) {
        return new DenseVector(copyOfRange(this.values, fromIndex, toIndex));
    }


}
