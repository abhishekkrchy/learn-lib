package linear.algebra.util;

import linear.algebra.vectors.Vector;
import linear.algebra.vectors.dense.DenseVector;

import java.util.stream.DoubleStream;

/**
 * The utility class Vectors
 * having utility method for
 * {@link Vector}
 * types.
 */
public class Vectors {
    /**
     * Converts a {@link DoubleStream}
     * to dense vector.
     *
     * @param doubleStream the double stream
     * @return the dense vector
     */
    public static DenseVector toDenseVector(DoubleStream doubleStream) {
        return new DenseVector(doubleStream.toArray());
    }
}
