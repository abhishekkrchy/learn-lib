package linear.algebra.statistics.errors;

import linear.algebra.vectors.dense.DenseVector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by abhishek on 24/05/17.
 */

public class ErrorsTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void markedErrorFunction() {
        DenseVector denseVector1 = new DenseVector(new double[]{1.0, 2.0, 3.0, 4.0});
        DenseVector denseVector2 = new DenseVector(new double[]{1.1, 2.2, 3.3, 4.4});
        //assertEquals(0.075, Errors.MARKED_ERROR_FUNCTION.apply(ErrorType.MSE).apply(3).apply(denseVector1, denseVector2).value(4.4), 0.00000001);
        // TODO : assertEquals(0.075, Errors.ERROR_FUNCTION.apply(ErrorType.MSE).apply(denseVector1, denseVector2), 0.00000001);
    }

    @After
    public void tearDown() {

    }
}
