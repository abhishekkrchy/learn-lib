package models.regression.linear;

import linear.algebra.vectors.dense.DenseVector;
import models.Model;
import models.regression.RegressionModel;
import optimizer.Optimizers;
import optimizer.grad.desc.GradientDescent;
import linear.algebra.statistics.Statistics;
import util.constants.enums.Optimizer;


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
