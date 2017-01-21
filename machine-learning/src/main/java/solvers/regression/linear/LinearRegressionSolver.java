package solvers.regression.linear;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.statistics.Statistics;
import linear.algebra.vectors.dense.DenseVector;
import models.Model;
import optimizer.grad.desc.GradientDescentOptimizer;
import solvers.Solver;
import solvers.regression.RegressionSolver;


public class LinearRegressionSolver extends RegressionSolver {
    public LinearRegressionSolver() {
    }

    @Override
    public Solver solve() {
        assignTrainAndTest();
        DenseVector entryPoint = Statistics.getNormalDistributionSamples(numberOfVariables + 1);
        model = gradientDescent(entryPoint);
        return this;
    }

    @Override
    public Model getModel() {
        return model;
    }

    private Model gradientDescent(DenseVector entryPoint) {
        try {
            optimizer=new GradientDescentOptimizer(entryPoint,maxIterations,new DenseMatrix(trainingX),errorType,new DenseVector(trainingY),regularizer,regularizationCoefficient,learningRate,minDescentLimit);
            optimizer.optimize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
