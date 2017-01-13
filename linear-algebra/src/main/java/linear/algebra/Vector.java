package linear.algebra;

import java.util.Iterator;
import java.util.stream.DoubleStream;

/**
 * A simple vector class.
 */
public interface Vector {

    int size();

    double value(int index);

    Iterator<Double> iterator();

    Vector slice(int fromIndex, int toIndex);

    DoubleStream stream();
}
