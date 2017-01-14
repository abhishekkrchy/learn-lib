package linear.algebra.util;

import linear.algebra.vectors.dense.DenseVector;

import java.util.stream.DoubleStream;

public class Vectors {
    public static DenseVector toDenseVector(DoubleStream doubleStream){
        return new DenseVector(doubleStream.toArray());
    }

}
