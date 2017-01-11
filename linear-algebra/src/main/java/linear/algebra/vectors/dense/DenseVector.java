package linear.algebra.vectors.dense;

import linear.algebra.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.*;


/**
 * Created by abhishek on 4/10/16.
 * <p>
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
        stream(values).forEach(System.out::println);
    }

    public DenseVector map(DoubleUnaryOperator function) {
        return new DenseVector(stream(this.values).map(function).toArray());
    }



    public void mapV(DoubleUnaryOperator function) {
        this.values = stream(this.values).map(function).toArray();
    }

    //public Function<DoubleUnaryOperator, DenseVector> mapOperator = this::map;

    public double reduce(DoubleBinaryOperator function) {
        return stream(this.values).reduce(0, function);
    }

    public DenseVector slice(int fromIndex, int toIndex) {
        return new DenseVector(copyOfRange(this.values, fromIndex, toIndex));
    }

}
