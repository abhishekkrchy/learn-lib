package models;

import linear.algebra.matrices.Matrix;
import linear.algebra.vectors.dense.DenseVector;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegressionModel implements Model {

    private final DenseVector factors;

    @Override
    public DenseVector predict(Matrix values) {
        DenseVector predictions = new DenseVector(values.getRows());
        for (int i = 0; i < values.getRows(); i++) {
            predictions.setValue(i, values.getRow(i).dotProduct(factors.tail()) + factors.head());
        }
        return predictions;
    }
}
