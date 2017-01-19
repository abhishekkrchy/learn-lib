package solvers;

import java.util.List;

/**
 * Created by abhishek on 29/9/16.
 */
public abstract class Solver {

    /**
     * If Solver built.
     */
    protected boolean modelBuilt;

    /**
     * Instantiates a new Solver.
     */
    protected Solver() {
    }

    /**
     * Gets number of variables.
     *
     * @return the number of variables
     */


    /**
     * Build.
     */
    public abstract Solver build();

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
    public abstract Solver loadDataSet(String path, boolean header) throws Exception;

    /**
     * Load.
     *
     * @param path the path
     */
    public abstract Solver load(String path);

    /**
     * Export.
     *
     * @param path the path
     */
    public abstract void export(String path);

    public abstract void assignTrainAndTest(double testingDataPercent);
}
