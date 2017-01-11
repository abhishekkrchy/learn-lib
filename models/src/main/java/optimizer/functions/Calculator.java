package optimizer.functions;

import linear.algebra.vectors.dense.DenseVector;
import models.constants.RegularizationFunction;

/**
 * Created by abhishek on 7/1/17.
 */
public class Calculator {
    private DenseVector denseVector1;
    private DenseVector denseVector2;
    private RegularizationFunction regularizationFunction;
    private double regularizationCoefficient;

    public Calculator() {
    }

    public Calculator(DenseVector denseVector1, DenseVector denseVector2, RegularizationFunction regularizationFunction, double regularizationCoefficient) {
        this.denseVector1 = denseVector1;
        this.denseVector2 = denseVector2;
        this.regularizationFunction = regularizationFunction;
        this.regularizationCoefficient = regularizationCoefficient;
    }

    public DenseVector getDenseVector1() {
        return denseVector1;
    }

    public void setDenseVector1(DenseVector denseVector1) {
        this.denseVector1 = denseVector1;
    }

    public DenseVector getDenseVector2() {
        return denseVector2;
    }

    public void setDenseVector2(DenseVector denseVector2) {
        this.denseVector2 = denseVector2;
    }

    public RegularizationFunction getRegularizationFunction() {
        return regularizationFunction;
    }

    public void setRegularizationFunction(RegularizationFunction regularizationFunction) {
        this.regularizationFunction = regularizationFunction;
    }

    public double getRegularizationCoefficient() {
        return regularizationCoefficient;
    }

    public void setRegularizationCoefficient(double regularizationCoefficient) {
        this.regularizationCoefficient = regularizationCoefficient;
    }
}
