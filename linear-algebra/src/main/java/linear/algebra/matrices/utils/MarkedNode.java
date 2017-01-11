package linear.algebra.matrices.utils;

/**
 * Created by abhishek on 11/1/17.
 */
public class MarkedNode {
    private double childNodeExponent = 1.0;
    private double variableCoefficient = 1.0;
    private double childNodeMultiplicand = 1.0;
    private double additiveConstant = 0.0;
    private AlgebraicFunction childFunctionalRelation;
    private MarkedNode childNode;

    public MarkedNode(double childNodeExponent, double variableCoefficient, double childNodeMultiplicand, double additiveConstant, AlgebraicFunction childFunctionalRelation, MarkedNode childNode) {
        this.childNodeExponent = childNodeExponent;
        this.variableCoefficient = variableCoefficient;
        this.childNodeMultiplicand = childNodeMultiplicand;
        this.additiveConstant = additiveConstant;
        this.childFunctionalRelation = childFunctionalRelation;
        this.childNode = childNode;
    }

    public MarkedNode(double variableCoefficient, double additiveConstant) {
        this.variableCoefficient = variableCoefficient;
        this.additiveConstant=additiveConstant;
    }

    public MarkedNode() {
    }

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

    public double getChildNodeExponent() {
        return childNodeExponent;
    }

    public void setChildNodeExponent(double childNodeExponent) {
        this.childNodeExponent = childNodeExponent;
    }

    public double getVariableCoefficient() {
        return variableCoefficient;
    }

    public void setVariableCoefficient(double variableCoefficient) {
        this.variableCoefficient = variableCoefficient;
    }

    public double getChildNodeMultiplicand() {
        return childNodeMultiplicand;
    }

    public void setChildNodeMultiplicand(double childNodeMultiplicand) {
        this.childNodeMultiplicand = childNodeMultiplicand;
    }

    public double getAdditiveConstant() {
        return additiveConstant;
    }

    public void setAdditiveConstant(double additiveConstant) {
        this.additiveConstant = additiveConstant;
    }

    public AlgebraicFunction getChildFunctionalRelation() {
        return childFunctionalRelation;
    }

    public void setChildFunctionalRelation(AlgebraicFunction childFunctionalRelation) {
        this.childFunctionalRelation = childFunctionalRelation;
    }

    public MarkedNode getChildNode() {
        return childNode;
    }

    public void setChildNode(MarkedNode childNode) {
        this.childNode = childNode;
    }
}
