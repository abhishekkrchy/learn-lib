package linear.algebra.vectors.dense;

import linear.algebra.Vector;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;


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
        Arrays.stream(values).forEach(System.out::println);
    }

    public DenseVector map(DoubleUnaryOperator function){
        this.values=Arrays.stream(this.values).map(function).toArray();
        return this;
    }

}
