package linear.algebra;

import java.util.Iterator;
import java.util.stream.DoubleStream;

/**
 * A simple vector class.
 */
public interface Vector {

    /**
     * Method for getting the size
     * (dimensions/no. of values)
     * contained in this matrix.
     *
     * @return as an integer,the
     * number of values in this
     * vector
     */
    int size();

    /**
     * Method for getting the value
     * at a particular position
     * inside the vector.
     *
     * @param index the index to query
     * @return the double value associated
     * with that index.
     */
    double value(int index);

    /**
     * Method for getting {@link Iterator} of
     * {@link Double} which returns the sequential
     * reference to the values contained.
     *
     * @return the iterator
     */
    Iterator<Double> iterator();

    /**
     * Method to slice vector from
     *
     * @param fromIndex to
     * @param toIndex   and
     * @return the new {@link Vector}
     * created using the sliced values.
     */
    Vector slice(int fromIndex, int toIndex);

    /**
     * Method to generate a primitive stream
     * out of values contained in the class
     * and @return the {@link DoubleStream}
     * generated.
     */
    DoubleStream stream();
}
