package linear.algebra.vectors.sparse;

import linear.algebra.expressions.Polynomial;
import linear.algebra.vectors.Vector;
import linear.algebra.vectors.dense.DenseVector;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.DoubleStream;

/**
 * A simple sparse vector class.
 */
public class SparseVector extends Vector {
    private double[] values;
    private int[] indices;


    //TODO
    public SparseVector(double[] values) {

    }

    public SparseVector(DenseVector denseVector) {
        this(denseVector.stream().toArray());
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
    public void setValue(int index, double value) {
        // TODO
    }

    @Override
    public DenseVector allExcept(int index) {
        return null;
    }

    @Override
    public Iterator<Double> iterator() {
        return null;
    }

    @Override
    public Vector slice(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public double dotProduct(Vector other) {
        return 0;
    }

    @Override
    public Polynomial dotProductWithVariable(Vector other, int varPos) {
        return null;
    }

    @Override
    public double head() {
        return 0;
    }

    @Override
    public DenseVector tail() {
        return null;
    }

    @Override
    public DoubleStream stream() {
        return null;
    }

}
