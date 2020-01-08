package solvers;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.vectors.dense.DenseVector;
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
    public abstract Solver loadDataSet(boolean header) throws Exception;

    public abstract DenseMatrix getTestingX();

    public abstract DenseVector getTestingY();

    protected abstract DenseVector entryPoint();
}
