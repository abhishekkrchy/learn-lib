package models;

import linear.algebra.Utils;
import linear.algebra.matrices.Matrix;
import linear.algebra.vectors.dense.DenseVector;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegressionModel implements Model {

    private final DenseVector factors;
    private final double intercept;

    @Override
    public DenseVector predict(Matrix values) {
        DenseVector predictions = new DenseVector(values.getRows());
        for (int i = 0; i < values.getRows(); i++) {
            predictions.setValue(i, Utils.dotProduct((DenseVector) values.getRow(i), factors) + intercept);
        }
        return predictions;
    }
}
