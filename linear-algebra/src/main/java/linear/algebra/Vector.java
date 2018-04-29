package linear.algebra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.DoubleStream;

/**
 * A simple vector class.
 */
public abstract class Vector {

    private static final String DEFAULT_INDENT_STRING = "  ";

    /**
     * Method for getting the size
     * (dimensions/no. of values)
     * contained in this matrix.
     *
     * @return as an integer,the
     * number of values in this
     * vector
     */
    public abstract int size();

    /**
     * Method for getting the value
     * at a particular position
     * inside the vector.
     *
     * @param index the index to query
     * @return the double value associated
     * with that index.
     */
    public abstract double value(int index);

    /**
     * Method for getting {@link Iterator} of
     * {@link Double} which returns the sequential
     * reference to the values contained.
     *
     * @return the iterator
     */
    public abstract Iterator<Double> iterator();

    /**
     * Method to slice vector from
     *
     * @param fromIndex to
     * @param toIndex   and
     * @return the new {@link Vector}
     * created using the sliced values.
     */
    public abstract Vector slice(int fromIndex, int toIndex);

    /**
     * Method to generate a primitive stream
     * out of values contained in the class
     * and @return the {@link DoubleStream}
     * generated.
     */
    public abstract DoubleStream stream();

    @Override
    public int hashCode() {
        List<Double> valuesList = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            valuesList.add(value(i));
        }
        return Arrays.hashCode(valuesList.toArray(new Double[0]));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }
        Vector other = (Vector) obj;
        if (size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (value(i) != other.value(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder vectorString = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            vectorString.append(value(i)).append(DEFAULT_INDENT_STRING);
        }
        return vectorString.toString();
    }
}
