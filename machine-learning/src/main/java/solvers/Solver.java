package solvers;

import linear.algebra.vectors.dense.DenseVector;
import models.Model;
import util.Data;

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
     * @return the solver
     */
    public abstract Solver loadDataSet();

    public abstract Data getData();

    protected abstract DenseVector entryPoint();
}
