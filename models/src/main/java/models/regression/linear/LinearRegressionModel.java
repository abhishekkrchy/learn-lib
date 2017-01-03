package models.regression.linear;

import models.Model;
import models.regression.RegressionModel;
import optimizer.GradientDescent;
import statistics.Distributions;


/**
 * Created by abhishek on 29/9/16.
 */
public class LinearRegressionModel extends RegressionModel {
    public LinearRegressionModel() {
    }

    @Override
    public Model build() {
        assignTrainAndTest();
       // initial
        double[] initial = Distributions.NORMAL_DISTRIBUTION.getNSmaples(numberOfVariables+1);
        gradientDescent(initial);
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
    private double[] gradientDescent(double[] initial){
        return GradientDescent.iterate(initial,this);
    }
}
