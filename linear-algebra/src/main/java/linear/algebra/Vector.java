package linear.algebra;

/**
 * Created by abhishek on 4/10/16.
 * <p>
 * A simple vector class.
 */
public abstract class Vector {
    protected int size;

    public Vector(int size) {
        this.size = size;
    }

    /**
     * Gets size of the vector.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets size of the vector.
     *
     * @param size the size to be set.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets value at a particular index in the vector.
     *
     * @param index the index
     * @return the double value at that index.
     */
    public abstract double value(int index);
}
