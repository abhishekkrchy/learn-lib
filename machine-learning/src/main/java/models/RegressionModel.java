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
    public DenseVector predict(Matrix values) throws Exception {
        DenseVector predictions = new DenseVector(values.getRows());
        for (int i = 0; i < values.getRows(); i++) {
            predictions.setValue(i, Utils.dotProduct((DenseVector) values.getRow(i), factors) + intercept);
        }
        return predictions;
    }

    @Override
    public void predict(String inpath, String outpath, boolean header) throws Exception {
        // TODO : fix this
//        List<List<Double>> dataSet = FileUtils.loadData(inpath, header);
//        List<Double> predictions = new ArrayList<>(dataSet.size());
//        for (List<Double> data : dataSet) {
//            predictions.add(predict(data));
//        }
//        ModelUtils.writePredictions(predictions, outpath);
    }

    @Override
    public void export(String path) {
        // TODO
    }
}
