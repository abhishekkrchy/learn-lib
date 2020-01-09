package models;

import linear.algebra.matrices.Matrix;
import linear.algebra.vectors.dense.DenseVector;

/**
 * The interface Model.
 * Used for representation of various models
 * created after training on a dataset.
 */
public interface Model {
    /**
     * Predict a primitive {@link Double} value
     * as prediction for the target value
     * when variables are given.
     *
     * @param values the values
     * @return the double
     */
    DenseVector predict(Matrix values);
}
