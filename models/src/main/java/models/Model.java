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

    /**
     * If Model built.
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


    /**
     * Build.
     */
    public abstract Model build();

    /**
     * Predict double.
     *
     * @param values the values
     * @return the double
     * @throws Exception the exception
     */
    public abstract double predict(List<Double> values) throws Exception;
    public abstract double predict(double[] values) throws Exception;
    public void predict(String inpath, String outpath) throws Exception{
        predict(inpath,outpath,false);
    }
    public abstract void predict(String inpath, String outpath,boolean header) throws Exception;
    public abstract Model loadDataSet(String path, boolean header) throws Exception;

    /**
     * Load.
     *
     * @param path the path
     */
    public abstract Model load(String path);

    /**
     * Export.
     *
     * @param path the path
     */
    public abstract void export(String path);

    public abstract void assignTrainAndTest(double testingDataPercent);
}
