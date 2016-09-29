package models;

import models.utils.CSVUtils;
import models.utils.ExceptionConstants;
import models.utils.ExceptionUtils;

import java.util.List;

/**
 * Created by abhishek on 29/9/16.
 */
public abstract class Model {
    private int numberOfVariables;
    private int numberOfExamples;

    private double[][] dataSet;

    public Model() {
    }

    public int getNumberOfVariables() {
        return numberOfVariables;
    }

    public int getNumberOfExamples() {
        return numberOfExamples;
    }

    public double[][] getDataSet() {
        return dataSet;
    }

    public abstract void build();

    public abstract void predict();

    public abstract void load(String path);

    public abstract void export(String path);

    public void loadDataSet(String path, boolean header) throws Exception {
        List<List<Double>> csvData = CSVUtils.loadData(path, header);
        numberOfExamples = csvData.size();
        if (numberOfExamples > 0) {
            numberOfVariables = csvData.get(0).size();
            if (numberOfVariables == 0)
                throw ExceptionUtils.getException(ExceptionConstants.EMPTY_CSV_DATA);
        } else {
            throw ExceptionUtils.getException(ExceptionConstants.EMPTY_CSV_DATA);
        }
        dataSet = new double[numberOfExamples][numberOfVariables];
        for (int i = 0; i < numberOfExamples; i++) {
            if (csvData.get(i).size() != numberOfVariables)
                throw ExceptionUtils.getException(ExceptionConstants.IMPROPER_CSV_DATA);
            for (int j = 0; j < numberOfVariables; j++) {
                dataSet[i][j] = csvData.get(i).get(j);
            }
        }
    }
}
