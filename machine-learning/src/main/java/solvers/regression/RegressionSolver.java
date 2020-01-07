package solvers.regression;

import linear.algebra.matrices.Matrix;
import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.Vector;
import models.Model;
import optimizer.Optimizer;
import solvers.regression.logistic.LogisticRegressionSolver;
import util.constants.exception.ExceptionConstants;
import linear.algebra.vectors.dense.DenseVector;
import solvers.Solver;
import util.ExceptionUtils;
import util.FileUtils;
import util.constants.enums.OptimizerType;
import util.constants.enums.Regularizer;
import util.constants.enums.ModelType;
import solvers.regression.linear.LinearRegressionSolver;

import java.util.*;

import static util.constants.enums.OptimizerType.GRADIENT_DESCENT;
import static util.constants.enums.Regularizer.NONE;


/**
 * The Basic Regression solver class
 * acting as a superclass to all Regression
 * Solvers currently {@link LinearRegressionSolver}
 * and {@link LogisticRegressionSolver}.
 */
public abstract class RegressionSolver extends Solver {
    /**
     * The Number of variables.
     */
    protected int numberOfVariables;
    /**
     * The Number of examples.
     */
    protected int numberOfExamples;
    /**
     * The Data set.
     */
    protected Matrix dataSet;
    /**
     * The Training x.
     */
    protected Matrix trainingX;
    /**
     * The Training y.
     */
    protected Vector trainingY;
    /**
     * The Testing x.
     */
    protected Matrix testingX;
    /**
     * The Testing y.
     */
    protected Vector testingY;
    /**
     * The Factors.
     */
    protected Vector factors;
    /**
     * The Optimizer type type.
     */
    protected OptimizerType optimizerTypeType = GRADIENT_DESCENT;
    /**
     * The Regularizer.
     */
    protected Regularizer regularizer = NONE;
    /**
     * The Max iterations.
     */
    protected int maxIterations = 100000;
    /**
     * The Learning rate.
     */
    protected double learningRate = 0.01;
    /**
     * The Regularization coefficient.
     */
    protected double regularizationCoefficient = 0.03;
    /**
     * The Min descent limit.
     */
    protected double minDescentLimit = 0.0001;
    /**
     * The Test indexes.
     */
    protected Set<Integer> testIndexes = new HashSet<>();
    /**
     * The Testing data percent.
     */
    protected double testingDataPercent = 25.0;
    /**
     * The Error type.
     */
    protected ErrorType errorType;
    /**
     * The Model.
     */
    protected Model model;
    /**
     * The Optimizer.
     */
    protected Optimizer optimizer;

    /**
     * Instantiates a new Regression model.
     */
    public RegressionSolver() {
    }

    /**
     * Gets model instance.
     *
     * @param modelType the model type name
     * @return the model instance
     * @throws Exception the exception
     *                   //TODO :: not needed?
     */
    public static Solver getModelInstance(ModelType modelType) throws Exception {
        switch (modelType) {
            case LINEAR_REGRESSION:
                return new LinearRegressionSolver();
            case LOGISTIC_REGRESSION:
                return new LogisticRegressionSolver();
            default:
                throw ExceptionUtils.getException(ExceptionConstants.EMPTY_MODEL);
        }
    }

    /**
     * Gets number of variables.
     *
     * @return the number of variables
     */
    public int getNumberOfVariables() {
        return numberOfVariables;
    }

    public void setNumberOfVariables(int numberOfExamples) {
        this.numberOfVariables = numberOfExamples;
    }

    /**
     * Gets number of examples.
     *
     * @return the number of examples
     */
    public int getNumberOfExamples() {
        return numberOfExamples;
    }


    public void setNumberOfExamples(int numberOfExamples) {
        this.numberOfExamples = numberOfExamples;
    }

    /**
     * Load data set.
     *
     * @param path   the path
     * @param header the header
     * @throws Exception the exception
     */
    public Solver loadDataSet(String path, boolean header) throws Exception {
        dataSet = FileUtils.loadData(path, header);
        numberOfExamples = dataSet.getRows();
        numberOfVariables = dataSet.getColumns();
        if (numberOfVariables == 0 || numberOfExamples == 0) {
            throw ExceptionUtils.getException(ExceptionConstants.EMPTY_CSV_DATA);
        }
        factors = new DenseVector(numberOfVariables);
        return this;
    }

    public void assignTrainAndTest(double testingDataPercent, boolean randomize) {
        this.testingDataPercent = testingDataPercent;
        assignTrainAndTest(randomize);
    }

    /**
     * Assign train and test.
     */
    public void assignTrainAndTest(boolean randomize) {
        int numberOfTestRows = (int) Math.floor(numberOfExamples * (testingDataPercent / 100));
        if (randomize) {
            Random random = new Random(new Date().getTime());
            while (testIndexes.size() < numberOfTestRows) {
                testIndexes.add(random.nextInt(numberOfExamples - 1));
            }
        } else {
            for (int i = numberOfExamples - 1; i > numberOfExamples - numberOfTestRows - 1; i--) {
                testIndexes.add(i);
            }
        }
        trainingX = new DenseMatrix(numberOfExamples - numberOfTestRows, numberOfVariables - 1);
        trainingY = new DenseVector(numberOfExamples - numberOfTestRows);
        testingX = new DenseMatrix(numberOfTestRows, numberOfVariables - 1);
        testingY = new DenseVector(numberOfTestRows);

        int testIndex = 0;
        int trainIndex = 0;

        for (int i = 0; i < dataSet.getRows(); i++) {
            if (testIndexes.contains(i)) {
                testingX.setRow(testIndex, dataSet.getRow(i).slice(0, dataSet.getColumns() - 1));
                testingY.setValue(testIndex++, dataSet.value(i, dataSet.getColumns() - 1));
            } else {
                trainingX.setRow(trainIndex, dataSet.getRow(i).slice(0, dataSet.getColumns() - 1));
                trainingY.setValue(trainIndex++, dataSet.value(i, dataSet.getColumns() - 1));
            }
            // TODO: destroy not needed dataSet[i] = null;
        }
    }

    /**
     * Get factors double [ ].
     *
     * @return the double [ ]
     * <p>
     * TODO :: not needed ? can be used from models only.
     */
    public Vector getFactors() {
        return factors;
    }

    /**
     * Sets factors.
     *
     * @param factors the factors
     */
    public void setFactors(Vector factors) {
        this.factors = factors;
    }

    /**
     * Gets optimizer type type.
     *
     * @return the optimizer type type
     */
    public OptimizerType getOptimizerTypeType() {
        return optimizerTypeType;
    }

    /**
     * Sets optimizer type type.
     *
     * @param optimizerTypeType the optimizer type type
     */
    public void setOptimizerTypeType(OptimizerType optimizerTypeType) {
        this.optimizerTypeType = optimizerTypeType;
    }

    /**
     * Gets regularizer.
     *
     * @return the regularizer
     */
    public Regularizer getRegularizer() {
        return regularizer;
    }

    /**
     * Sets regularizer.
     *
     * @param regularizer the regularizer
     */
    public void setRegularizer(Regularizer regularizer) {
        this.regularizer = regularizer;
    }

    /**
     * Gets max iterations.
     *
     * @return the max iterations
     */
    public int getMaxIterations() {
        return maxIterations;
    }

    /**
     * Sets max iterations.
     *
     * @param maxIterations the max iterations
     */
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    /**
     * Gets learning rate.
     *
     * @return the learning rate
     */
    public double getLearningRate() {
        return learningRate;
    }

    /**
     * Sets learning rate.
     *
     * @param learningRate the learning rate
     */
    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    /**
     * Gets regularization coefficient.
     *
     * @return the regularization coefficient
     */
    public double getRegularizationCoefficient() {
        return regularizationCoefficient;
    }

    /**
     * Sets regularization coefficient.
     *
     * @param regularizationCoefficient the regularization coefficient
     */
    public void setRegularizationCoefficient(double regularizationCoefficient) {
        this.regularizationCoefficient = regularizationCoefficient;
    }

    /**
     * Gets min descent limit.
     *
     * @return the min descent limit
     */
    public double getMinDescentLimit() {
        return minDescentLimit;
    }

    /**
     * Sets min descent limit.
     *
     * @param minDescentLimit the min descent limit
     */
    public void setMinDescentLimit(double minDescentLimit) {
        this.minDescentLimit = minDescentLimit;
    }


    /**
     * Gets error type.
     *
     * @return the error type
     */
    public ErrorType getErrorType() {
        return errorType;
    }

    /**
     * Sets error type.
     *
     * @param errorType the error type
     */
    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public Matrix getTestingX() {
        return testingX;
    }

    // TODO : validate
    public Vector getTestingY() {
        return testingY;
    }
}