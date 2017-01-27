package solvers.regression;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.util.constants.enums.ErrorType;
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
    protected double[][] dataSet;
    /**
     * The Training x.
     */
    protected double[][] trainingX;
    /**
     * The Training y.
     */
    protected double[] trainingY;
    /**
     * The Testing x.
     */
    protected double[][] testingX;
    /**
     * The Testing y.
     */
    protected double[] testingY;
    /**
     * The Factors.
     */
    protected double[] factors;
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
    protected double learningRate = 0.001;
    /**
     * The Regularization coefficient.
     */
    protected double regularizationCoefficient = 0.01;
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
     * //TODO :: not needed?
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

    /**
     * Gets number of examples.
     *
     * @return the number of examples
     */
    public int getNumberOfExamples() {
        return numberOfExamples;
    }

    /**
     * Get data set double [ ] [ ].
     *
     * @return the double [ ] [ ]
     */
    public double[][] getDataSet() {
        return dataSet;
    }



    /**
     * Load data set.
     *
     * @param path   the path
     * @param header the header
     * @throws Exception the exception
     */
    public Solver loadDataSet(String path, boolean header) throws Exception {
        List<List<Double>> csvData = FileUtils.loadData(path, header);
        numberOfExamples = csvData.size();
        if (numberOfExamples > 0) {
            numberOfVariables = csvData.get(0).size() - 1;
            if (numberOfVariables == 0)
                throw ExceptionUtils.getException(ExceptionConstants.EMPTY_CSV_DATA);
        } else {
            throw ExceptionUtils.getException(ExceptionConstants.EMPTY_CSV_DATA);
        }
        dataSet = new double[numberOfExamples][numberOfVariables];
        for (int i = 0; i < numberOfExamples; i++) {
            if (csvData.get(i).size() != numberOfVariables)
                throw ExceptionUtils.getException(ExceptionConstants.IMPROPER_CSV_DATA);
            for (int j = 0; j < numberOfVariables; j++) {
                dataSet[i][j] = csvData.get(i).get(j);
            }
        }
        factors = new double[numberOfVariables];
        return this;
    }

    public void assignTrainAndTest(double testingDataPercent) {
        this.testingDataPercent = testingDataPercent;
        assignTrainAndTest();
    }

    /**
     * Assign train and test.
     */
    public void assignTrainAndTest() {
        int numberOfTestRows = (int) Math.floor(numberOfExamples * (testingDataPercent / 100));
        Random random = new Random(new Date().getTime());
        while (testIndexes.size() < numberOfTestRows) {
            testIndexes.add(random.nextInt(numberOfExamples - 1));
        }
        trainingX = new double[numberOfExamples - numberOfTestRows][numberOfVariables - 1];
        trainingY = new double[numberOfExamples - numberOfTestRows];
        int testIndex = 0;
        int trainIndex = 0;
        for (int i = 0; i < dataSet.length; i++) {
            if (testIndexes.contains(i)) {
                testingX[testIndex] = Arrays.copyOfRange(dataSet[i], 0, dataSet[i].length - 1);
                testingY[testIndex++] = dataSet[i][dataSet[i].length - 1];
            } else {
                trainingX[trainIndex] = Arrays.copyOfRange(dataSet[i], 0, dataSet[i].length - 1);
                trainingY[trainIndex++] = dataSet[i][dataSet[i].length - 1];
            }
            dataSet[i] = null;
        }
    }

    /**
     * Get factors double [ ].
     *
     * @return the double [ ]
     *
     * TODO :: not needed ? can be used from models only.
     */
    public double[] getFactors() {
        return factors;
    }

    /**
     * Sets factors.
     *
     * @param factors the factors
     */
    public void setFactors(double[] factors) {
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
     * Gets training x.
     *
     * @return the training x
     */
    public DenseMatrix getTrainingX() {
        return new DenseMatrix(trainingX);
    }

    /**
     * Sets training x.
     *
     * @param trainingX the training x
     */
    public void setTrainingX(double[][] trainingX) {
        this.trainingX = trainingX;
    }

    /**
     * Get testing x double [ ] [ ].
     *
     * @return the double [ ] [ ]
     */
    public double[][] getTestingX() {
        return testingX;
    }

    /**
     * Sets testing x.
     *
     * @param testingX the testing x
     */
    public void setTestingX(double[][] testingX) {
        this.testingX = testingX;
    }

    /**
     * Gets training y.
     *
     * @return the training y
     */
    public DenseVector getTrainingY() {
        return new DenseVector(trainingY);
    }

    /**
     * Sets training y.
     *
     * @param trainingY the training y
     */
    public void setTrainingY(double[] trainingY) {
        this.trainingY = trainingY;
    }

    /**
     * Get testing y double [ ].
     *
     * @return the double [ ]
     */
    public double[] getTestingY() {
        return testingY;
    }

    /**
     * Sets testing y.
     *
     * @param testingY the testing y
     */
    public void setTestingY(double[] testingY) {
        this.testingY = testingY;
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
}