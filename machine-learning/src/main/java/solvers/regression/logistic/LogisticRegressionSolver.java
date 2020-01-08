//package solvers.regression.logistic;
//
//import linear.algebra.util.constants.enums.ErrorType;
//import linear.algebra.vectors.dense.DenseVector;
//import lombok.Builder;
//import models.Model;
//import solvers.Solver;
//import solvers.regression.RegressionSolver;
//import util.constants.enums.OptimizerType;
//import util.constants.enums.Regularizer;
//
///**
// * Created by abhishek on 29/9/16.
// */
//public class LogisticRegressionSolver extends RegressionSolver {
//
//    @Builder
//    protected LogisticRegressionSolver(String inputFile, ErrorType errorType, OptimizerType optimizerType, Regularizer regularizer, int maxIterations, double learningRate, double regularizationCoefficient, double minDescentLimit, double testingDataPercent) throws Exception {
//        super(inputFile);
//        //, errorType, optimizerType, regularizer, maxIterations, learningRate, regularizationCoefficient, minDescentLimit, testingDataPercent);
//    }
//
//    @Override
//    protected DenseVector entryPoint() {
//        return null;
//    }
//
//}
