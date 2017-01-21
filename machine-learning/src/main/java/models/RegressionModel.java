package models;

import linear.algebra.vectors.dense.DenseVector;
import util.ExceptionUtils;
import util.FileUtils;
import util.ModelUtils;
import util.constants.exception.ExceptionConstants;

import java.util.ArrayList;
import java.util.List;

public class RegressionModel implements Model {
    private boolean modelBuilt;
    private DenseVector factors;
    private double intercept;

    @Override
    public double predict(Object values) throws Exception {
        DenseVector denseVector = (DenseVector) values;
        if (!modelBuilt)
            throw ExceptionUtils.getException(ExceptionConstants.MODEL_NOT_BUILT);
        double output = intercept;
        for (int i=0;i<factors.size();i++) {
            output += factors.value(i) * denseVector.value(i++);
        }
        return output;
    }

    @Override
    public void predict(String inpath, String outpath, boolean header) throws Exception {
        List<List<Double>> dataSet = FileUtils.loadData(inpath, header);
        List<Double> predictions = new ArrayList<>(dataSet.size());
        for (List<Double> data : dataSet) {
            predictions.add(predict(data));
        }
        ModelUtils.writePredictions(predictions, outpath);
    }

    @Override
    public void export(String path) {

    }

    @Override
    public void load(String path) {

    }

    @Override
    public void build() {

    }
}
