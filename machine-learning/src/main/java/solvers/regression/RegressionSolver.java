package solvers.regression;

import linear.algebra.matrices.Matrix;
import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import models.Model;
import optimizer.Optimizer;
import optimizer.Optimizers;
import solvers.Solver;
import util.ExceptionUtils;
import util.FileUtils;
import util.constants.enums.OptimizerType;
import util.constants.enums.Regularizer;
import util.constants.exception.ExceptionConstants;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static linear.algebra.util.constants.enums.ErrorType.MSE;
import static util.constants.enums.OptimizerType.GRADIENT_DESCENT;
import static util.constants.enums.Regularizer.NONE;


/**
 * The Basic Regression solver class
 * acting as a superclass to all Regression
 * Solvers currently {@link LinearRegressionSolver}
 * and {@link LogisticRegressionSolver}.
 */

@Builder
@AllArgsConstructor
public class RegressionSolver extends Solver {

    private String inputFile;

    @Builder.Default
    private OptimizerType optimizerType = GRADIENT_DESCENT;
    @Builder.Default
    private Regularizer regularizer = NONE;
    @Builder.Default
    private int maxIterations = 1000;
    @Builder.Default
    private double learningRate = 0.01;
    @Builder.Default
    private double regularizationCoefficient = 0.03;
    @Builder.Default
    private double minDescentLimit = 0.0001;
    @Builder.Default
    private double testingDataPercent = 25.0;
    @Builder.Default
    private ErrorType errorType = MSE;


    private Matrix dataSet;
    private DenseMatrix trainingX;
    private DenseVector trainingY;
    private DenseMatrix testingX;
    private DenseVector testingY;

    //@Builder
//    protected RegressionSolver(String inputFile, ErrorType errorType, OptimizerType optimizerType, Regularizer regularizer, int maxIterations, double learningRate, double regularizationCoefficient, double minDescentLimit, double testingDataPercent) throws Exception {
////        this.inputFile = inputFile;
////        this.errorType = errorType;
////        this.optimizerType = optimizerType;
////        this.regularizer = regularizer;
////        this.maxIterations = maxIterations;
////        this.learningRate = learningRate;
////        this.regularizationCoefficient = regularizationCoefficient;
////        this.minDescentLimit = minDescentLimit;
////        this.testingDataPercent = testingDataPercent;
////        // TODO : hard-coded value
//        loadDataSet(false);
//    }

    @Override
    public DenseMatrix getTestingX() {
        return testingX;
    }

    @Override
    public DenseVector getTestingY() {
        return testingY;
    }

    @Override
    protected DenseVector entryPoint() {
        // TODO : remove hardcoding
        loadDataSet(false);
        return new DenseVector(new double[]{1, 2, 3, 4});//Statistics.getNormalDistributionSamples(dataSet.getColumns());
    }

    /**
     * Load data set.
     *
     * @param path   the path
     * @param header the header
     * @throws Exception the exception
     */
    public Solver loadDataSet(boolean header) {
        dataSet = FileUtils.loadData(inputFile, header);
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
}