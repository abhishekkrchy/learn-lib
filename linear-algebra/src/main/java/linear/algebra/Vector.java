package linear.algebra;

import java.util.Iterator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleUnaryOperator;

/**
 * A simple vector class.
 */
public interface Vector {

    int size();

    double value(int index);

    void forEach(DoubleConsumer action);

    Iterator<Double> iterator();

    Vector map(DoubleUnaryOperator doubleUnaryOperator);

    double reduce(double identity, DoubleBinaryOperator doubleUnaryOperator);

    Vector slice(int fromIndex, int toIndex);
}
