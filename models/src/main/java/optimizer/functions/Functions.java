package optimizer.functions;

import linear.algebra.matrices.utils.AlgebraicFunction;
import linear.algebra.matrices.utils.MarkedNode;
import linear.algebra.vectors.dense.DenseVector;
import models.constants.RegularizationFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by abhishek on 7/1/17.
 */
public class Functions {

    public static MarkedNode lossFunction(Calculator calculator, int varPos) {
        MarkedNode loss = meanSquaredError(calculator,varPos);
        if (calculator.getRegularizationFunction().equals(RegularizationFunction.L1)) {
            double cons=0;
            cons += calculator.getRegularizationCoefficient() * (calculator.getDenseVector2().slice(0, calculator.getDenseVector2().getSize()).map(Math::abs).reduce(Double::sum) - Math.abs(calculator.getDenseVector2().value(varPos)));
            MarkedNode innerNode = new MarkedNode(1,cons);
            loss.setChildNode(innerNode);
            loss.setChildFunctionalRelation(AlgebraicFunction.ADD);
        }
        else if (calculator.getRegularizationFunction().equals(RegularizationFunction.L2)) {
            double cons=0;
            cons += (0.5 * calculator.getRegularizationCoefficient()) *
                    (((calculator.getDenseVector2().slice(0, calculator.getDenseVector2().getSize()).map(x -> Math.pow(x, 2)))
                            .reduce(Double::sum)-Math.pow(calculator.getDenseVector2().value(varPos),2.0)) / calculator.getDenseVector2().getSize() - 1);
            MarkedNode innerNode = new MarkedNode(1,cons);
            loss.setChildNode(innerNode);
            loss.setChildFunctionalRelation(AlgebraicFunction.ADD);
            loss.setChildNodeMultiplicand(calculator.getDenseVector2().getSize() - 1);
        }
        return loss;
    }

    public static double meanSquaredError(Calculator calculator) {
        double total = 0.0;
        for (int i = 0; i < calculator.getDenseVector1().getSize(); i++) {
            total += Math.pow(calculator.getDenseVector1().value(i) - calculator.getDenseVector2().value(i), 2.0);
        }
        return total / calculator.getDenseVector1().getSize();
    }

    public static MarkedNode meanSquaredError(Calculator calculator, int varPos) {
        double total = 0.0;
        MarkedNode markedNode = new MarkedNode();
        for (int i = 0; i < calculator.getDenseVector1().getSize(); i++) {
            if (i == varPos) {
                MarkedNode temp = new MarkedNode(-1, calculator.getDenseVector1().value(i));
                markedNode.setChildNode(temp);
            } else {
                total += Math.pow(calculator.getDenseVector1().value(i) - calculator.getDenseVector2().value(i), 2.0);
            }
        }
        markedNode.setAdditiveConstant(total / calculator.getDenseVector1().getSize());
        markedNode.setChildNodeExponent(2.0);
        markedNode.setChildNodeMultiplicand(1/(calculator.getDenseVector1().getSize()));
        markedNode.setChildFunctionalRelation(AlgebraicFunction.ADD);
        return markedNode;
    }


}
