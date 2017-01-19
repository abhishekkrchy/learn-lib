package solvers.regression.linear;

import linear.algebra.vectors.dense.DenseVector;
import solvers.Model;
import solvers.regression.RegressionModel;
import optimizer.Optimizers;
import linear.algebra.statistics.Statistics;


public class LinearRegressionModel extends RegressionModel {
    public LinearRegressionModel() {
    }

    @Override
    public Model build() {
        assignTrainAndTest();
        DenseVector entryPoint = Statistics.getNormalDistributionSamples(numberOfVariables+1);
        gradientDescent(entryPoint);
        modelBuilt = true;
        return this;
    }

    @Override
    public Model load(String path) {
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
