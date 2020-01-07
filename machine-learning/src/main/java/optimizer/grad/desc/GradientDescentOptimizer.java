package optimizer.grad.desc;

import linear.algebra.Utils;
import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.Vectors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.expressions.Polynomial;
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
    private DenseVector errorVector;

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

    private DenseVector padded(DenseMatrix training, DenseVector theta) throws Exception {
        DenseVector product = Utils.multiply(training, theta.slice(1, theta.size()));
        return Vectors.toDenseVector(product.stream().map(x -> x + theta.value(0)));
    }

    private DenseMatrix onepad(DenseMatrix denseMatrix) {
        double[][] doubles = new double[denseMatrix.getRows()][denseMatrix.getColumns() + 1];
        for (int i = 0; i < denseMatrix.getRows(); i++) {
            doubles[i][0] = 1d;
            for (int j = 0; j < denseMatrix.getColumns(); j++) {
                doubles[i][j + 1] = denseMatrix.value(i, j);
            }
        }
        return new DenseMatrix(doubles);
    }

    private Polynomial[] paddedYYYY(DenseMatrix training, DenseVector theta, int varPos, DenseVector trainingY) throws Exception {
        Polynomial[] products = Utils.multiply(onepad(training), theta, varPos);
        for (int i = 0; i < trainingY.size(); i++) {
            products[i].term(-1 * trainingY.value(i));
        }
        return Arrays.stream(products).map(Polynomial::squared).map(x -> x.divideCoefficients(products.length)).toArray(Polynomial[]::new);
    }

    @Override
    public Model optimize() {
        int iterations = 0;
        double[] errors = new double[maxIterations + 1];
        try {
            errors[iterations] = Errors.ERROR_FUNCTION.apply(errorType).apply(padded(trainingX, initial), trainingY);
            DenseVector denseVector = initial;
            System.out.println(initial + "is initial");

            while (iterations < maxIterations) {

                System.out.println(" it " + iterations);

                double[] delta = new double[denseVector.size()];

                for (int i = 0; i < denseVector.size(); i++) {


                    Polynomial loss = Arrays.stream(paddedYYYY(trainingX, denseVector, i, trainingY)).reduce(new Polynomial(2), Polynomial::add);

                    System.out.println("loss poly is" + loss);

                    if (regularizer == Regularizer.L2) {
                        loss.term(0.5 * regularizationCoefficient * (denseVector.slice(1, denseVector.size()).stream().map(x -> x * x).sum()));
                    }

                    double changedValDer = loss.derivative(denseVector.value(i));
                    System.out.println("loss deri is" + loss.derivative() + " " + changedValDer);
                    delta[i] = changedValDer;
                }

                double[] newDv = new double[denseVector.size()];
                for (int i = 0; i < denseVector.size(); i++) {
                    newDv[i] = denseVector.value(i) - (learningRate * delta[i]);
                }

                denseVector = new DenseVector(newDv);

                System.out.println("initial dv tf to" + denseVector + " delta " + Arrays.toString(delta) + " errors " + Arrays.toString(errors));


                errors[++iterations] = Errors.ERROR_FUNCTION.apply(errorType)
                        .apply(padded(trainingX, denseVector), trainingY);

                System.out.println(Arrays.toString(errors) + " = errors");

                if (Math.abs(errors[iterations] - errors[iterations - 1]) <= minDescentLimit)
                    break;
            }
            errorVector = new DenseVector(errors);
            System.out.println(errorVector + " = errors");
            RegressionModel model = new RegressionModel(denseVector.slice(1, denseVector.size()), denseVector.value(0));
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
