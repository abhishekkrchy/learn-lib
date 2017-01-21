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
 * Created by abhishek on 29/9/16.
 */
public abstract class RegressionSolver extends Solver {
    protected int numberOfVariables;
    protected int numberOfExamples;
    protected double[][] dataSet;
    protected double[][] trainingX;
    protected double[] trainingY;
    protected double[][] testingX;
    protected double[] testingY;
    protected double[] factors;
    protected OptimizerType optimizerTypeType = GRADIENT_DESCENT;
    protected Regularizer regularizer = NONE;
    protected int maxIterations = 100000;
    protected double learningRate = 0.001;
    protected double regularizationCoefficient = 0.01;
    protected double minDescentLimit = 0.0001;
    protected Set<Integer> testIndexes = new HashSet<>();
    protected double testingDataPercent = 25.0;
    protected ErrorType errorType;
    protected Model model;
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

    public double[] getFactors() {
        return factors;
    }

    public void setFactors(double[] factors) {
        this.factors = factors;
    }

    public OptimizerType getOptimizerTypeType() {
        return optimizerTypeType;
    }

    public void setOptimizerTypeType(OptimizerType optimizerTypeType) {
        this.optimizerTypeType = optimizerTypeType;
    }

    public Regularizer getRegularizer() {
        return regularizer;
    }

    public void setRegularizer(Regularizer regularizer) {
        this.regularizer = regularizer;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public double getRegularizationCoefficient() {
        return regularizationCoefficient;
    }

    public void setRegularizationCoefficient(double regularizationCoefficient) {
        this.regularizationCoefficient = regularizationCoefficient;
    }

    public double getMinDescentLimit() {
        return minDescentLimit;
    }

    public void setMinDescentLimit(double minDescentLimit) {
        this.minDescentLimit = minDescentLimit;
    }

    public DenseMatrix getTrainingX() {
        return new DenseMatrix(trainingX);
    }

    public void setTrainingX(double[][] trainingX) {
        this.trainingX = trainingX;
    }

    public double[][] getTestingX() {
        return testingX;
    }

    public void setTestingX(double[][] testingX) {
        this.testingX = testingX;
    }

    public DenseVector getTrainingY() {
        return new DenseVector(trainingY);
    }

    public void setTrainingY(double[] trainingY) {
        this.trainingY = trainingY;
    }

    public double[] getTestingY() {
        return testingY;
    }

    public void setTestingY(double[] testingY) {
        this.testingY = testingY;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}