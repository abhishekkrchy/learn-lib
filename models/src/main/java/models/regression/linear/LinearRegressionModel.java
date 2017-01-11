package models.regression.linear;

import linear.algebra.vectors.dense.DenseVector;
import models.Model;
import models.regression.RegressionModel;
import optimizer.GradientDescent;
import statistics.util.Statistics;


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
            GradientDescent.iterate(entryPoint,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
