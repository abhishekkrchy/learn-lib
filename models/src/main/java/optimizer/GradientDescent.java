package optimizer;

import linear.algebra.Utils;
import linear.algebra.matrices.dense.TwoDimensionalMatrix;
import linear.algebra.vectors.dense.DenseVector;
import models.regression.RegressionModel;
import optimizer.functions.Calculator;
import optimizer.functions.Functions;
import statistics.CostFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static linear.algebra.Utils.multiply;

/**
 * Created by abhishek on 3/1/17.
 */
public class GradientDescent {
    public static DenseVector iterate(DenseVector initial, RegressionModel regressionModel) throws Exception {
        int iterations = 0;
        double[] errors = new double[regressionModel.getMaxIterations()];
        errors[iterations]= Functions.MEAN_SQUARED_ERROR.apply(new Calculator(multiply(regressionModel.getTrainingX(),initial).map(x -> x + initial.value(0)),regressionModel.getTrainingY(),regressionModel.getRegularizationFunction(),regressionModel.getRegularizationCoefficient()),0).calc(initial.value(0));
        //TwoDimensionalMatrix trainingXMatrix=regressionModel.getTrainingX();
       // DenseVector initialPrediction=new DenseVector(Arrays.copyOfRange(initial,1,initial.length));
        /*DenseVector predictionLocal=multiply(regressionModel.getTrainingX(),initialPrediction).map(x -> x + initial[0]);
        predictions.map(x -> x + initial[0]);*//**//*
        Function lossFunction= CostFunctions.*/
        while (iterations<regressionModel.getMaxIterations()){
            double changeFactor=Functions.LOSS_FUNCTION.apply(new Calculator(multiply(regressionModel.getTrainingX(),initial).map(x -> x + initial.value(0)),regressionModel.getTrainingY(),regressionModel.getRegularizationFunction(),regressionModel.getRegularizationCoefficient()),0).derivative(initial.value(0));
            DenseVector denseVector=initial.map(x->x-(changeFactor*regressionModel.getLearningRate()));
            errors[++iterations]=Functions.MEAN_SQUARED_ERROR.apply(new Calculator(multiply(regressionModel.getTrainingX(),denseVector).map(x -> x + denseVector.value(0)),regressionModel.getTrainingY(),regressionModel.getRegularizationFunction(),regressionModel.getRegularizationCoefficient()),0).calc(denseVector.value(0));
        }
        return new DenseVector(errors);
    }
}
