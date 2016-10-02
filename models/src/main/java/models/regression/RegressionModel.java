package models.regression;

import models.Model;
import models.constants.ModelTypes;
import models.regression.linear.LinearRegressionModel;
import models.regression.logistic.LogisticRegressionModel;
import models.utils.ExceptionConstants;
import models.utils.ExceptionUtils;

/**
 * Created by abhishek on 29/9/16.
 */
public abstract class RegressionModel extends Model {


    /**
     * Instantiates a new Regression model.
     */
    public RegressionModel() {
    }

    /**
     * Gets model instance.
     *
     * @param modelTypeName the model type name
     * @return the model instance
     * @throws Exception the exception
     */
    public static Model getModelInstance(ModelTypes.NAME modelTypeName) throws Exception {
        switch (modelTypeName) {
            case LINEAR_REGRESSION:
                return new LinearRegressionModel();
            case LOGISTIC_REGRESSION:
                return new LogisticRegressionModel();
            default:
                throw ExceptionUtils.getException(ExceptionConstants.EMPTY_MODEL);
        }
    }
}