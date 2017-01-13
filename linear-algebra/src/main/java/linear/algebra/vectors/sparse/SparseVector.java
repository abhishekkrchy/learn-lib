package linear.algebra.vectors.sparse;

import linear.algebra.Vector;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleUnaryOperator;

/**
 * A simple sparse vector class.
 */
public class SparseVector implements Vector {
    private double[] values;
    private int[] indices;


    //TODO
    public SparseVector(double[] values) {

    }

    /**
     * Instantiates a new Sparse vector.
     *
     * @param values  the non-zero values in the vector.
     * @param indices the indices of non-zero values in the vector.
     */
    public SparseVector(double[] values, int[] indices) {
        this.values = values;
        this.indices = indices;
    }

    @Override
    public int size() {
        return 0;
    }

    public double value(int index) {
        int searchIndex = Arrays.binarySearch(indices, index);
        return indices[searchIndex == -1 ? 0 : searchIndex] == index ? values[searchIndex] : 0;
    }

    @Override
    public void forEach(DoubleConsumer action) {

    }

    @Override
    public Iterator<Double> iterator() {
        return null;
    }

    @Override
    public Vector map(DoubleUnaryOperator doubleUnaryOperator) {
        return null;
    }

    @Override
    public double reduce(double identity, DoubleBinaryOperator doubleUnaryOperator) {
        return 0;
    }

    @Override
    public Vector slice(int fromIndex, int toIndex) {
        return null;
    }
}
