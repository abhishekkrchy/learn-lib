package solvers;

import models.Model;

/**
 * Created by abhishek on 29/9/16.
 */
public abstract class Solver {

    protected Solver() {
    }

    public abstract Solver solve();

    public abstract Solver loadDataSet(String path, boolean header) throws Exception;

    public abstract void assignTrainAndTest(double testingDataPercent);

    public abstract Model getModel();
}
