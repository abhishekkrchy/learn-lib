package optimizer.grad.desc;

import linear.algebra.expressions.Polynomial;
import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.Vectors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import models.Model;
import models.RegressionModel;
import optimizer.Optimizer;
import util.constants.enums.Regularizer;

import java.util.Arrays;


public class GradientDescentOptimizer implements Optimizer {

    private DenseVector initial;
    private int maxIterations;
    private DenseMatrix trainingX;
    private ErrorType errorType;
    private DenseVector trainingY;
    private Regularizer regularizer;
    private double regularizationCoefficient;
    private double learningRate;
    private double minDescentLimit;

    public GradientDescentOptimizer(DenseVector initial, int maxIterations, DenseMatrix trainingX, ErrorType errorType, DenseVector trainingY, Regularizer regularizer, double regularizationCoefficient, double learningRate, double minDescentLimit) {
        this.initial = initial;
        this.maxIterations = maxIterations;
        this.trainingX = trainingX;
        this.errorType = errorType;
        this.trainingY = trainingY;
        this.regularizer = regularizer;
        this.regularizationCoefficient = regularizationCoefficient;
        this.learningRate = learningRate;
        this.minDescentLimit = minDescentLimit;
    }

    private DenseVector multiplyAndAddIntercept(DenseMatrix training, DenseVector factors) {
        DenseVector product = training.multiply(factors.tail());
        return Vectors.toDenseVector(product.stream().map(x -> x + factors.head()));
    }

    private Polynomial[] polynomialError(DenseMatrix training, DenseVector factors, int varPos, DenseVector trainingY) {
        Polynomial[] products = training.addColumn(0).multiplyWithVariable(factors, varPos);
        for (int i = 0; i < trainingY.size(); i++) {
            products[i].term(-1 * trainingY.value(i));
        }
        return Arrays.stream(products).map(Polynomial::squared).map(x -> x.divideCoefficients(products.length)).toArray(Polynomial[]::new);
    }

    @Override
    public Model optimize() {

        int iterations = 0;
        DenseVector factors = initial;
        System.out.println(initial + "is initial");

        double[] errors = new double[maxIterations + 1];
        errors[0] = Errors.ERROR_FUNCTION.apply(errorType).apply(multiplyAndAddIntercept(trainingX, initial), trainingY);


        while (iterations < maxIterations) {

            System.out.println(" it " + iterations);

            double[] delta = new double[factors.size()];
            for (int i = 0; i < factors.size(); i++) {
                Polynomial loss = Arrays.stream(polynomialError(trainingX, factors, i, trainingY)).reduce(new Polynomial(2), Polynomial::add);
                System.out.println("loss poly is" + loss);
                if (regularizer == Regularizer.L2) {
                    loss.term(0.5 * regularizationCoefficient * (factors.tail()).stream().map(x -> Math.pow(x, 2)).sum());
                }

                double derivativeOfLoss = loss.derivative(factors.value(i));
                System.out.println("loss deri is" + loss.derivative() + " " + derivativeOfLoss);
                delta[i] = derivativeOfLoss;
            }

            double[] newFactors = new double[factors.size()];
            for (int i = 0; i < factors.size(); i++) {
                newFactors[i] = factors.value(i) - (learningRate * delta[i]);
            }

            factors = new DenseVector(newFactors);

            System.out.println("initial dv tf to" + factors + " delta " + Arrays.toString(delta) + " errors " + Arrays.toString(errors));


            errors[++iterations] = Errors.ERROR_FUNCTION.apply(errorType)
                    .apply(multiplyAndAddIntercept(trainingX, factors), trainingY);

            System.out.println(Arrays.toString(errors) + " = errors");

            if (Math.abs(errors[iterations] - errors[iterations - 1]) <= minDescentLimit) {
                System.out.println("Min descent limit reached");
                break;
            }
        }
        DenseVector errorVector = new DenseVector(errors);
        System.out.println(errorVector + " = errors");
        return new RegressionModel(factors);
    }
}
