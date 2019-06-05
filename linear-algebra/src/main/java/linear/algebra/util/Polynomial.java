package linear.algebra.util;

import linear.algebra.util.constants.enums.AlgebraicFunction;

/**
 * The type Polynomial.
 */
public class Polynomial {

    private double exponent;
    private double multiplier;
    private AlgebraicFunction function;
    private Polynomial nextTerm;

    public Polynomial(double exponent, double multiplier, AlgebraicFunction function, Polynomial nextTerm) {
        this(exponent, multiplier);
        this.function = function;
        this.nextTerm = nextTerm;
    }

    public Polynomial(double exponent, double multiplier) {
        this.exponent = exponent;
        this.multiplier = multiplier;
    }

    /**
     * Instantiates a new Marked node.
     */
    public Polynomial() {
    }

    /**
     * Calculate the double
     * value of this markedNode.
     *
     * @param val the val
     * @return the double
     */
    public double value(double val) {
        double returnVal = multiplier * Math.pow(val, exponent);
        if (function != null && nextTerm != null) {
            switch (function) {
                case ADD:
                    returnVal = returnVal + nextTerm.value(val);
                    break;
                case SUB:
                    returnVal = returnVal - nextTerm.value(val);
                    break;
                case MUL:
                    returnVal = returnVal * nextTerm.value(val);
                    break;
                case DIV:
                    returnVal = returnVal / nextTerm.value(val);
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
        double returnVal = multiplier * exponent * Math.pow(val, exponent - 1);
        if (function == null || nextTerm == null) {
            return returnVal;
        }

        switch (function) {
            case ADD:
                return returnVal + nextTerm.derivative(val);
            case SUB:
                return returnVal - nextTerm.derivative(val);
            case MUL:
                return (multiplier * Math.pow(val, exponent)) * nextTerm.derivative(val) + returnVal * nextTerm.value(val);
            case DIV:
                return (returnVal * nextTerm.value(val) - (multiplier * Math.pow(val, exponent)) * nextTerm.derivative(val)) / Math
                        .pow(nextTerm.value(val), 2);
            default:
                throw new IllegalArgumentException("Invalid function type.");
        }
    }

    @Override
    public String toString(){
        String val = "(";
        if (multiplier!=1d) {
            val+=multiplier;
        }
        if (exponent!=0d){
            val+="x^" + exponent;
        }
        if (multiplier==1d&& exponent==0d){
            val+="1";
        }
        val+=")";
        if (nextTerm!=null && function!=null) {
            val+=function;
            val += nextTerm.toString();
        }
        return val;
    }
}
