package util;

import linear.algebra.matrices.Matrix;
import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.vectors.dense.DenseVector;
import util.constants.exception.LearningException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Data {

    private final Matrix matrix;
    private final double testingDataPercent;
    private final List<Integer> testRows = new ArrayList<>();

    public Data(Matrix matrix, double testingDataPercent) {
        this.matrix = matrix;
        this.testingDataPercent = testingDataPercent;
        validateAndInitialize();
    }

    public int numColumns() {
        return matrix.numColumns();
    }

    private void validateAndInitialize() {
        if (matrix.numRows() == 0 || matrix.numColumns() == 0) {
            throw new LearningException("Empty data set");
        }
        assignTrainAndTest();
    }


    /**
     * Assign train and test.
     */
    private void assignTrainAndTest() {
        Random random = new Random(new Date().getTime());
        testRows.addAll(random.ints((int) Math.floor(matrix.numRows() * (testingDataPercent / 100)), 0, matrix.numRows()).boxed().collect(Collectors.toSet()));
    }


    private int testRowsPassed(int rowNumber) {
        return (int) testRows.stream().filter(x -> x <= rowNumber).count();
    }

    public double trainYVal(int rowNumber) {
        return matrix.value(testRowsPassed(rowNumber) + rowNumber, matrix.numColumns() - 1);
    }

    public double testYVal(int rowNumber) {
        return matrix.value(testRows.get(rowNumber), matrix.numColumns() - 1);
    }

    public double trainXVal(int rowNumber, int columnNumber) {
        return matrix.value(testRowsPassed(rowNumber) + rowNumber, columnNumber);
    }

    public double testXVal(int rowNumber, int columnNumber) {
        return matrix.value(testRows.get(rowNumber), columnNumber);
    }

    public DenseMatrix trainingX() {
        double[][] trainingX = new double[matrix.numRows() - testRows.size()][matrix.numColumns() - 1];
        for (int i = 0; i < trainingX.length; i++) {
            for (int j = 0; j < trainingX[0].length; j++) {
                trainingX[i][j] = trainXVal(i, j);
            }
        }
        return new DenseMatrix(trainingX);
    }

    public DenseVector trainingY() {
        DenseVector trainingY = new DenseVector(matrix.numRows() - testRows.size());
        for (int i = 0; i < trainingY.size(); i++) {
            trainingY.setValue(i, trainYVal(i));
        }
        return trainingY;
    }

    public DenseMatrix testingX() {
        double[][] trainingX = new double[testRows.size()][matrix.numColumns() - 1];
        for (int i = 0; i < trainingX.length; i++) {
            for (int j = 0; j < trainingX[0].length; j++) {
                trainingX[i][j] = testXVal(i, j);
            }
        }
        return new DenseMatrix(trainingX);
    }

    public DenseVector testingY() {
        DenseVector trainingY = new DenseVector(testRows.size());
        for (int i = 0; i < trainingY.size(); i++) {
            trainingY.setValue(i, testYVal(i));
        }
        return trainingY;
    }
}
