package linear.algebra.util;

import linear.algebra.util.constants.enums.AlgebraicFunction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by abhishek on 24/05/17.
 */
public class PolynomialTest {
    @Test
    public void calc() {
        Polynomial polynomial = new Polynomial(3, 1);
        assertEquals(1000000, polynomial.value(100), 0.00000001);

        Polynomial polynomial2 = new Polynomial(1, 2, AlgebraicFunction.ADD, polynomial);
        assertEquals(1000200, polynomial2.value(100), 0.0000001);

        polynomial2 = new Polynomial(1, 2, AlgebraicFunction.SUB, polynomial);
        assertEquals(-1009800, polynomial2.value(100), 0.0000001);

        polynomial2 = new Polynomial(1, 2, AlgebraicFunction.MUL, polynomial);
        assertEquals(200000000, polynomial2.value(100), 0.0000001);

        polynomial2 = new Polynomial(1, 2, AlgebraicFunction.DIV, polynomial);
        assertEquals(200 / 1000000, polynomial2.value(100), 0.0000001);
    }

    @Test
    public void derivative() {
        Polynomial polynomial = new Polynomial(3, 1);
        assertEquals(30000, polynomial.derivative(100), 0.00000001);

        Polynomial polynomial2 = new Polynomial(1, 2, AlgebraicFunction.ADD, polynomial);
        assertEquals(30002, polynomial2.derivative(100), 0.0000001);

        polynomial2 = new Polynomial(1, 2, AlgebraicFunction.SUB, polynomial);
        assertEquals(-29998, polynomial2.derivative(100), 0.00000001);

        polynomial2 = new Polynomial(1, 2, AlgebraicFunction.MUL, polynomial);
        assertEquals(8000000, polynomial2.derivative(100), 0.00000001);

        polynomial2 = new Polynomial(1, 2, AlgebraicFunction.DIV, polynomial);
        assertEquals(-4.0 / 1000000.0, polynomial2.derivative(100), 0.00000001);
    }
}