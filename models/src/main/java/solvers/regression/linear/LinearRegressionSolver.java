package solvers.regression.linear;

import linear.algebra.vectors.dense.DenseVector;
import solvers.Solver;
import solvers.regression.RegressionSolver;
import optimizer.Optimizers;
import linear.algebra.statistics.Statistics;


public class LinearRegressionSolver extends RegressionSolver {
    public LinearRegressionSolver() {
    }

    @Override
    public Solver build() {
        assignTrainAndTest();
        DenseVector entryPoint = Statistics.getNormalDistributionSamples(numberOfVariables+1);
        gradientDescent(entryPoint);
        modelBuilt = true;
        return this;
    }

    @Override
    public Solver load(String path) {
        return this;
    }

    @Override
    public void export(String path) {

    }
    private DenseVector gradientDescent(DenseVector entryPoint){
        try {
            Optimizers.optimize.apply(optimizerType).apply(entryPoint,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
