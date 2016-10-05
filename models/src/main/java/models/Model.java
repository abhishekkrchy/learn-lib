package models;

import utils.CSVUtils;
import utils.ExceptionConstants;
import utils.ExceptionUtils;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 29/9/16.
 */
public abstract class Model {
    private int numberOfVariables;
    private int numberOfExamples;

    private double[][] dataSet;
    private double[] factors;
    /**
     * The Model built.
     */
    protected boolean modelBuilt;

    /**
     * Instantiates a new Model.
     */
    protected Model() {
    }

    /**
     * Gets number of variables.
     *
     * @return the number of variables
     */
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

    /**
     * Build.
     */
    public abstract void build();

    /**
     * Predict double.
     *
     * @param values the values
     * @return the double
     * @throws Exception the exception
     */
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
     * @throws Exception the exception
     */
    public void predict(String inpath, String outpath) throws Exception {
        predict(inpath, outpath, false);
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
     * Load.
     *
     * @param path the path
     */
    public abstract void load(String path);

    /**
     * Export.
     *
     * @param path the path
     */
    public abstract void export(String path);

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
