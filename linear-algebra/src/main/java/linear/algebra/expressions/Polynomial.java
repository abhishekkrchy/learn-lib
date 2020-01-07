package linear.algebra.expressions;

public class Polynomial {

    private final double[] coefficients;

    public Polynomial(int degree) {
        this.coefficients = new double[degree + 1];
    }

    public Polynomial term(double coefficient) {
        return term(coefficient, 0);
    }

    public Polynomial term(double coefficient, int exponent) {
        coefficients[exponent] = coefficients[exponent] + coefficient;
        return this;
    }

    // TODO: seperate single valued variable class
    public Polynomial squared() {
        return new Polynomial(2)
                .term(coefficients[0] * coefficients[0])
                .term(2 * coefficients[0] * coefficients[1], 1)
                .term(coefficients[1] * coefficients[1], 2);
    }

    public Polynomial derivative() {
        Polynomial polynomial = new Polynomial(coefficients.length - 2);
        for (int i = 1; i < coefficients.length; i++) {
            polynomial.term(coefficients[i] * i, i - 1);
        }
        return polynomial;
    }

    public double value(double val) {
        double total = 0d;
        for (int i = 0; i < coefficients.length; i++) {
            total += (coefficients[i] * (Math.pow(val, i)));
        }
        return total;
    }

    public double derivative(double val) {
        return derivative().value(val);
    }

    public Polynomial divideCoefficients(int div) {
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] /= div;
        }
        return this;
    }

    public Polynomial add(Polynomial other) {
        Polynomial polynomial = new Polynomial(Math.max(coefficients.length - 1, other.coefficients.length - 1));
        for (int i = 0; i < polynomial.coefficients.length; i++) {
            polynomial.term(coefficients[i] + other.coefficients[i], i);
        }
        return polynomial;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            stringBuilder.append("+(").append(coefficients[i]).append("x^").append(i).append(")");
        }
        return stringBuilder.substring(1);
    }
}
