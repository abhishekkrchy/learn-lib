package linear.algebra.util;

import linear.algebra.util.constants.enums.AlgebraicFunction;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by abhishek on 24/05/17.
 */
public class MarkedNodeTest {
    @Test
    public void calc() throws Exception {
        MarkedNode markedNode = new MarkedNode(3,1);
        assertEquals(301,markedNode.calc(100),0.00000001);

        MarkedNode markedNode2=new MarkedNode(1,2,1,100, AlgebraicFunction.ADD,markedNode);
        assertEquals(601,markedNode2.calc(100),0.0000001);
        markedNode2.setChildFunctionalRelation(AlgebraicFunction.SUB);
        assertEquals(97,markedNode2.calc(2),0.00000001);
        markedNode2.setChildFunctionalRelation(AlgebraicFunction.MUL);
        assertEquals(728,markedNode2.calc(2),0.00000001);
        markedNode2.setChildFunctionalRelation(AlgebraicFunction.DIV);
        assertEquals(104.0/7.0,markedNode2.calc(2),0.00000001);
        markedNode2.setChildNodeMultiplicand(3);
        markedNode2.setChildNodeExponent(2);
        assertEquals(104.0/147.0,markedNode2.calc(2),0.00000001);


    }

    @Test
    public void derivative() throws Exception {
        MarkedNode markedNode = new MarkedNode(3,1);
        assertEquals(3,markedNode.derivative(100),0.00000001);

        MarkedNode markedNode2=new MarkedNode(1,2,1,100, AlgebraicFunction.ADD,markedNode);
        assertEquals(5,markedNode2.derivative(100),0.0000001);
        markedNode2.setChildFunctionalRelation(AlgebraicFunction.SUB);
        assertEquals(-1,markedNode2.derivative(2),0.00000001);
        markedNode2.setChildFunctionalRelation(AlgebraicFunction.MUL);
        assertEquals(326,markedNode2.derivative(2),0.00000001);
        markedNode2.setChildFunctionalRelation(AlgebraicFunction.DIV);
        markedNode2.setChildNodeMultiplicand(10);
        markedNode2.setChildNodeExponent(3);
        assertEquals(-910.0/2560.0,markedNode2.derivative(1),0.00000001);
    }

}