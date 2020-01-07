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
     * @throws Exception the exception
     */
    DenseVector predict(Matrix values) throws Exception;

    /**
     * Predict on a dataset stored in a file.
     *
     * @param inpath  the inpath
     * @param outpath the outpath
     * @param header  the header
     * @throws Exception the exception
     */
    void predict(String inpath, String outpath, boolean header) throws Exception;

    /**
     * Export this model to a file.
     *
     * @param path the path
     */
    void export(String path);
}
