package solvers.regression;

import linear.algebra.statistics.Statistics;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import models.Model;
import optimizer.Optimizer;
import optimizer.Optimizers;
import solvers.Solver;
import util.Data;
import util.FileUtils;
import util.constants.enums.OptimizerType;
import util.constants.enums.Regularizer;

import static linear.algebra.util.constants.enums.ErrorType.MSE;
import static util.constants.enums.OptimizerType.GRADIENT_DESCENT;
import static util.constants.enums.Regularizer.NONE;


/**
 * The Basic Regression solver class
 * acting as a entry for all Regression
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

    private Data data;

    @Override
    protected DenseVector entryPoint() {
        loadDataSet();
        return Statistics.getNormalDistributionSamples(data.numColumns());
    }

    /**
     * Load data set.
     */
    public Solver loadDataSet() {
        this.data = FileUtils.loadData(inputFile, testingDataPercent);
        return this;
    }

    @Override
    public Data getData() {
        return data;
    }

    public Model solve() {
        Optimizer optimizer = Optimizers.optimizer(optimizerType, entryPoint(), maxIterations, errorType, data, regularizer, regularizationCoefficient, learningRate, minDescentLimit);
        return optimizer.optimize();
    }
}