package linear.algebra.matrices.dense;

import linear.algebra.vectors.dense.DenseVector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by abhishek.kumar
 */
public class DenseMatrixTest {

    private DenseMatrix denseMatrix;

    @Before
    public void setUp() {
        denseMatrix = new DenseMatrix(new double[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        });
    }

    @Test
    public void value() {
        assertEquals(1.0, denseMatrix.value(0, 0), 0.00000001);
    }

    @Test
    public void transpose() {
        DenseMatrix transpose = denseMatrix.transpose();
        assertEquals(1.0, transpose.value(0, 0), 0.0000001);
        assertEquals(4.0, transpose.value(0, 1), 0.0000001);
        assertEquals(7.0, transpose.value(0, 2), 0.0000001);
        assertEquals(2.0, transpose.value(1, 0), 0.0000001);
        assertEquals(5.0, transpose.value(1, 1), 0.0000001);
        assertEquals(8.0, transpose.value(1, 2), 0.0000001);
        assertEquals(3.0, transpose.value(2, 0), 0.0000001);
        assertEquals(6.0, transpose.value(2, 1), 0.0000001);
        assertEquals(9.0, transpose.value(2, 2), 0.0000001);
        assertEquals(1.0, denseMatrix.value(0, 0), 0.0000001);
        assertEquals(2.0, denseMatrix.value(0, 1), 0.0000001);
        assertEquals(3.0, denseMatrix.value(0, 2), 0.0000001);
        assertEquals(4.0, denseMatrix.value(1, 0), 0.0000001);
        assertEquals(5.0, denseMatrix.value(1, 1), 0.0000001);
        assertEquals(6.0, denseMatrix.value(1, 2), 0.0000001);
        assertEquals(7.0, denseMatrix.value(2, 0), 0.0000001);
        assertEquals(8.0, denseMatrix.value(2, 1), 0.0000001);
        assertEquals(9.0, denseMatrix.value(2, 2), 0.0000001);
    }

    @Test
    public void getRow() {
        DenseVector denseVector = denseMatrix.getRow(0);
        assertEquals(new DenseVector(new double[]{1.0, 2.0, 3.0}), denseVector);
    }

    @Test
    public void getColumn() {
        DenseVector denseVector = denseMatrix.getColumn(0);
        assertEquals(new DenseVector(new double[]{1.0, 4.0, 7.0}), denseVector);
    }
}