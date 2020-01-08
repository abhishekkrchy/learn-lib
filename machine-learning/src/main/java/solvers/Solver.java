package solvers;

import models.Model;

/**
 * Created by abhishek on 29/9/16.
 */
public abstract class Solver {

    /**
     * Instantiates a new Solver.
     */
    protected Solver() {
    }

    /**
     * Solve the ml-equation.
     *
     * @return the solver
     */
    public abstract Model solve();

    /**
     * Load data set.
     *
     * @param path   the path
     * @param header the header
     * @return the solver
     * @throws Exception the exception
     */
    public abstract Solver loadDataSet(String path, boolean header) throws Exception;

    /**
     * Assign train and test.
     *
     * @param testingDataPercent the testing data percent
     */
    public abstract void assignTrainAndTest(double testingDataPercent, boolean randomize);

    /**
     * Gets model.
     *
     * @return the model
     */
    public abstract Model getModel();

    public abstract void predict(String opFilePath, boolean headers) throws Exception;
}
