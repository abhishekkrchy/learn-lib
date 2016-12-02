package models.regression;

import models.Model;
import models.constants.Optimizer;
import models.constants.RegularizationFunction;
import models.constants.ModelType;
import models.regression.linear.LinearRegressionModel;
import models.regression.logistic.LogisticRegressionModel;
import utils.CSVUtils;
import utils.ExceptionConstants;
import utils.ExceptionUtils;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static models.constants.Optimizer.GRADIENT_DESCENT;
import static models.constants.RegularizationFunction.NONE;

/**
 * Created by abhishek on 29/9/16.
 */
public abstract class RegressionModel extends Model {
    private int numberOfVariables;
    private int numberOfExamples;
    private double[][] dataSet;
    private double[] factors;
    private Optimizer optimizer = GRADIENT_DESCENT;
    private RegularizationFunction regularizationFunction = NONE;
    private int maxIterations = 100000;
    private double learningRate = 0.001;
    private double regularizationCoefficient = 0.01;
    private double minDescentLimit = 0.0001;

    /**
     * Instantiates a new Regression model.
     */
    public RegressionModel() {
    }

    /**
     * Gets model instance.
     *
     * @param modelType the model type name
     * @return the model instance
     * @throws Exception the exception
     */
    public static Model getModelInstance(ModelType modelType) throws Exception {
        switch (modelType) {
            case LINEAR_REGRESSION:
                return new LinearRegressionModel();
            case LOGISTIC_REGRESSION:
                return new LogisticRegressionModel();
            default:
                throw ExceptionUtils.getException(ExceptionConstants.EMPTY_MODEL);
        }
    }

    public int getNumberOfVariables() {
        return numberOfVariables;
    }

    /**
     * Gets number of examples.
     *
     * @return the number of examples
     */
    public int getNumberOfExamples() {
        return numberOfExamples;
    }

    /**
     * Get data set double [ ] [ ].
     *
     * @return the double [ ] [ ]
     */
    public double[][] getDataSet() {
        return dataSet;
    }



    public double predict(List<Double> values) throws Exception {
        if (!modelBuilt)
            throw ExceptionUtils.getException(ExceptionConstants.MODEL_NOT_BUILT);
        double output = 0.0;
        int i = 0;
        for (double factor : factors) {
            output += factor * values.get(i++);
        }
        return output;
    }

    /**
     * Predict double.
     *
     * @param values the values
     * @return the double
     * @throws Exception the exception
     */
    public double predict(double[] values) throws Exception {
        if (!modelBuilt)
            throw ExceptionUtils.getException(ExceptionConstants.MODEL_NOT_BUILT);
        double output = 0.0;
        int i = 0;
        for (double factor : factors) {
            output += factor * values[i++];
        }
        return output;
    }
    /**
     * Predict.
     *
     * @param inpath  the inpath
     * @param outpath the outpath
     * @param header  the header
     * @throws Exception the exception
     */
    public void predict(String inpath, String outpath, boolean header) throws Exception {
        List<List<Double>> dataSet = CSVUtils.loadData(inpath, header);
        List<Double> predictions = new ArrayList<>(dataSet.size());
        for (List<Double> data : dataSet) {
            predictions.add(predict(data));
        }
        Utils.writePredictions(predictions,outpath);
    }
    /**
     * Load data set.
     *
     * @param path   the path
     * @param header the header
     * @throws Exception the exception
     */
    public void loadDataSet(String path, boolean header) throws Exception {
        List<List<Double>> csvData = CSVUtils.loadData(path, header);
        numberOfExamples = csvData.size();
        if (numberOfExamples > 0) {
            numberOfVariables = csvData.get(0).size();
            if (numberOfVariables == 0)
                throw ExceptionUtils.getException(ExceptionConstants.EMPTY_CSV_DATA);
        } else {
            throw ExceptionUtils.getException(ExceptionConstants.EMPTY_CSV_DATA);
        }
        dataSet = new double[numberOfExamples][numberOfVariables];
        for (int i = 0; i < numberOfExamples; i++) {
            if (csvData.get(i).size() != numberOfVariables)
                throw ExceptionUtils.getException(ExceptionConstants.IMPROPER_CSV_DATA);
            for (int j = 0; j < numberOfVariables; j++) {
                dataSet[i][j] = csvData.get(i).get(j);
            }
        }
        factors = new double[numberOfVariables];
    }
}