package linear.algebra.util;

import linear.algebra.util.constants.enums.AlgebraicFunction;

/**
 * The type Marked node.
 */
//TODO :: can be optimized further.
public class MarkedNode {
    private double childNodeExponent = 1.0;
    private double variableCoefficient = 1.0;
    private double childNodeMultiplicand = 1.0;
    private double additiveConstant = 0.0;
    private AlgebraicFunction childFunctionalRelation;
    private MarkedNode childNode;

    /**
     * Instantiates a new Marked node.
     *
     * @param childNodeExponent       the child node exponent
     * @param variableCoefficient     the variable coefficient
     * @param childNodeMultiplicand   the child node multiplicand
     * @param additiveConstant        the additive constant
     * @param childFunctionalRelation the child functional relation
     * @param childNode               the child node
     */
    public MarkedNode(double childNodeExponent, double variableCoefficient, double childNodeMultiplicand, double additiveConstant, AlgebraicFunction childFunctionalRelation, MarkedNode childNode) {
        this.childNodeExponent = childNodeExponent;
        this.variableCoefficient = variableCoefficient;
        this.childNodeMultiplicand = childNodeMultiplicand;
        this.additiveConstant = additiveConstant;
        this.childFunctionalRelation = childFunctionalRelation;
        this.childNode = childNode;
    }

    /**
     * Instantiates a new Marked node.
     *
     * @param variableCoefficient the variable coefficient
     * @param additiveConstant    the additive constant
     */
    public MarkedNode(double variableCoefficient, double additiveConstant) {
        this.variableCoefficient = variableCoefficient;
        this.additiveConstant=additiveConstant;
    }

    /**
     * Instantiates a new Marked node.
     */
    public MarkedNode() {
    }

    /**
     * Calculate the double
     * value of this markedNode.
     *
     * @param val the val
     * @return the double
     */
    public double calc(double val) {
        double returnVal = (variableCoefficient * val) + additiveConstant;
        if (childNode != null) {
            switch (childFunctionalRelation) {
                case ADD:
                    returnVal = returnVal + childNodeMultiplicand * Math.pow(childNode.calc(val), childNodeExponent);
                    break;
                case SUB:
                    returnVal = returnVal - childNodeMultiplicand * Math.pow(childNode.calc(val), childNodeExponent);
                    break;
                case MUL:
                    returnVal = returnVal * childNodeMultiplicand * Math.pow(childNode.calc(val), childNodeExponent);
                    break;
                case DIV:
                    returnVal = returnVal / childNodeMultiplicand * Math.pow(childNode.calc(val), childNodeExponent);
            }
        }
        return returnVal;
    }

    /**
     * calculated gradient as a double
     * with respect to the index for
     * which it was marked.
     *
     * @param val the val
     * @return the double
     */
    public double derivative(double val) {
        if (childFunctionalRelation == null)
            return variableCoefficient * val;
        switch (childFunctionalRelation) {
            case ADD:
                return (variableCoefficient * val) + (childNodeMultiplicand) * ((childNodeExponent) * Math.pow((childNode.calc(val)), childNodeExponent - 1) * childNode.derivative(val));
            case SUB:
                return (variableCoefficient * val) - (childNodeMultiplicand) * ((childNodeExponent) * Math.pow((childNode.calc(val)), childNodeExponent - 1) * childNode.derivative(val));
            case MUL:
                return ((variableCoefficient * val) * (childNodeMultiplicand) * Math.pow((childNode.calc(val)), childNodeExponent)) + (calc(val) * (childNodeMultiplicand) * (childNodeExponent) * Math.pow(childNode.calc(val), childNodeExponent - 1) * childNode.derivative(val));
            case DIV:
                return ((variableCoefficient * val) / (childNodeMultiplicand) * Math.pow((childNode.calc(val)), childNodeExponent)) + (calc(val) / ((childNodeMultiplicand) * (childNodeExponent) * Math.pow(childNode.calc(val), childNodeExponent - 1) * childNode.derivative(val)));
            default:
                return Double.MIN_VALUE;
        }
    }

    /**
     * Gets child node exponent.
     *
     * @return the child node exponent
     */
    public double getChildNodeExponent() {
        return childNodeExponent;
    }

    /**
     * Sets child node exponent.
     *
     * @param childNodeExponent the child node exponent
     */
    public void setChildNodeExponent(double childNodeExponent) {
        this.childNodeExponent = childNodeExponent;
    }

    /**
     * Gets variable coefficient.
     *
     * @return the variable coefficient
     */
    public double getVariableCoefficient() {
        return variableCoefficient;
    }

    /**
     * Sets variable coefficient.
     *
     * @param variableCoefficient the variable coefficient
     */
    public void setVariableCoefficient(double variableCoefficient) {
        this.variableCoefficient = variableCoefficient;
    }

    /**
     * Gets child node multiplicand.
     *
     * @return the child node multiplicand
     */
    public double getChildNodeMultiplicand() {
        return childNodeMultiplicand;
    }

    /**
     * Sets child node multiplicand.
     *
     * @param childNodeMultiplicand the child node multiplicand
     */
    public void setChildNodeMultiplicand(double childNodeMultiplicand) {
        this.childNodeMultiplicand = childNodeMultiplicand;
    }

    /**
     * Gets additive constant.
     *
     * @return the additive constant
     */
    public double getAdditiveConstant() {
        return additiveConstant;
    }

    /**
     * Sets additive constant.
     *
     * @param additiveConstant the additive constant
     */
    public void setAdditiveConstant(double additiveConstant) {
        this.additiveConstant = additiveConstant;
    }

    /**
     * Gets child functional relation.
     *
     * @return the child functional relation
     */
    public AlgebraicFunction getChildFunctionalRelation() {
        return childFunctionalRelation;
    }

    /**
     * Sets child functional relation.
     *
     * @param childFunctionalRelation the child functional relation
     */
    public void setChildFunctionalRelation(AlgebraicFunction childFunctionalRelation) {
        this.childFunctionalRelation = childFunctionalRelation;
    }

    /**
     * Gets child node.
     *
     * @return the child node
     */
    public MarkedNode getChildNode() {
        return childNode;
    }

    /**
     * Sets child node.
     *
     * @param childNode the child node
     */
    public void setChildNode(MarkedNode childNode) {
        this.childNode = childNode;
    }
}
