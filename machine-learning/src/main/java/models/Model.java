package models;

/**
 * The interface Model.
 * Used for representation of various models
 * created after training on a dataset.
 */
//TODO :: add Serialization and PMML support
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
    double predict(Object values) throws Exception;

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

    /**
     * Load a model stored in a file.
     *
     * @param path the path
     */
    void load(String path);

    /**
     * Build a model.
     */
    void build();
}
