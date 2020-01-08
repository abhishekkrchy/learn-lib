package solvers.regression;

import linear.algebra.matrices.Matrix;
import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.Vector;
import lombok.Builder;
import models.Model;
import optimizer.Optimizer;
import optimizer.Optimizers;
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

    protected final OptimizerType optimizerType;
    protected final Regularizer regularizer;
    protected final int maxIterations;
    protected final double learningRate;
    protected final double regularizationCoefficient;
    protected final double minDescentLimit;
    protected final double testingDataPercent;
    protected ErrorType errorType;


    private Matrix dataSet;
    protected DenseMatrix trainingX;
    protected DenseVector trainingY;
    protected DenseMatrix testingX;
    protected DenseVector testingY;

    protected RegressionSolver(String inputFile, ErrorType errorType, OptimizerType optimizerType, Regularizer regularizer, int maxIterations, double learningRate, double regularizationCoefficient, double minDescentLimit, double testingDataPercent) throws Exception {
        this.errorType = errorType;
        this.optimizerType = optimizerType;
        this.regularizer = regularizer;
        this.maxIterations = maxIterations;
        this.learningRate = learningRate;
        this.regularizationCoefficient = regularizationCoefficient;
        this.minDescentLimit = minDescentLimit;
        this.testingDataPercent = testingDataPercent;
        // TODO : hard-coded value
        loadDataSet(inputFile, false);
    }

    /**
     * Gets model instance.
     *
     * @param modelType the model type name
     * @return the model instance
     * @throws Exception the exception
     *                   //TODO :: not needed?
     */
//    public static Solver getModelInstance(ModelType modelType) throws Exception {
//        switch (modelType) {
//            case LINEAR_REGRESSION:
//                return new LinearRegressionSolver();
//            case LOGISTIC_REGRESSION:
//                return new LogisticRegressionSolver();
//            default:
//                throw ExceptionUtils.getException(ExceptionConstants.EMPTY_MODEL);
//        }
//    }

    /**
     * Load data set.
     *
     * @param path   the path
     * @param header the header
     * @throws Exception the exception
     */
    public Solver loadDataSet(String path, boolean header) {
        dataSet = FileUtils.loadData(path, header);
        if (dataSet.getRows() == 0 || dataSet.getColumns() == 0) {
            throw ExceptionUtils.getException(ExceptionConstants.EMPTY_CSV_DATA);
        }
        // TODO :  hard-coded
        assignTrainAndTest(false);
        return this;
    }

    /**
     * Assign train and test.
     */
    private void assignTrainAndTest(boolean randomize) {
        int numberOfExamples = dataSet.getRows();
        int numberOfTestRows = (int) Math.floor(numberOfExamples * (testingDataPercent / 100));
        int numberOfVariables = dataSet.getColumns();

        Set<Integer> testIndexes = testIndexes(randomize);

        trainingX = new DenseMatrix(numberOfExamples - numberOfTestRows, numberOfVariables - 1);
        trainingY = new DenseVector(numberOfExamples - numberOfTestRows);
        testingX = new DenseMatrix(numberOfTestRows, numberOfVariables - 1);
        testingY = new DenseVector(numberOfTestRows);

        int testIndex = 0;
        int trainIndex = 0;

        // TODO : move to matrix class

        for (int i = 0; i < dataSet.getRows(); i++) {
            if (testIndexes.contains(i)) {
                testingX.setRow(testIndex, dataSet.getRow(i).slice(0, numberOfVariables - 1));
                testingY.setValue(testIndex++, dataSet.value(i, numberOfVariables - 1));
            } else {
                trainingX.setRow(trainIndex, dataSet.getRow(i).slice(0, numberOfVariables - 1));
                trainingY.setValue(trainIndex++, dataSet.value(i, numberOfVariables - 1));
            }
            // TODO: destroy not needed dataSet[i] = null;
        }
    }

    // TODO : move to utils
    private Set<Integer> testIndexes(boolean randomize) {
        int numberOfExamples = dataSet.getRows();
        int numberOfTestRows = (int) Math.floor(numberOfExamples * (testingDataPercent / 100));
        Set<Integer> testIndexes = new HashSet<>();
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
        return testIndexes;
    }

    public Model solve() {
        Optimizer optimizer = Optimizers.optimizer(optimizerType, entryPoint(), maxIterations, trainingX, errorType, trainingY, regularizer, regularizationCoefficient, learningRate, minDescentLimit);
        return optimizer.optimize();
    }

    protected abstract DenseVector entryPoint();


//    public Matrix getTestingX() {
//        return testingX;
//    }
//
//    // TODO : validate
//    public Vector getTestingY() {
//        return testingY;
//    }
}