package linear.algebra.vectors.sparse;

import linear.algebra.Vector;

import java.util.Arrays;

/**
 * A simple sparse vector class.
 */
public class SparseVector extends Vector {
    private double[] values;
    private int[] indices;

    /**
     * Instantiates a new Sparse vector.
     *
     * @param size the size of the vector.
     */
    public SparseVector(int size) {
        super(size);
    }

    /**
     * Instantiates a new Sparse vector.
     *
     * @param size    the size of the vector.
     * @param values  the non-zero values in the vector.
     * @param indices the indices of non-zero values in the vector.
     */
    public SparseVector(int size, double[] values, int[] indices) {
        super(size);
        this.values = values;
        this.indices = indices;
    }

    public double value(int index) {
        int searchIndex = Arrays.binarySearch(indices, index);
        return indices[searchIndex == -1 ? 0 : searchIndex] == index ? values[searchIndex] : 0;
    }
}
