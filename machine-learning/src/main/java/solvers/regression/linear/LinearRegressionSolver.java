package solvers.regression.linear;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.statistics.Statistics;
import linear.algebra.util.Vectors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import models.Model;
import optimizer.grad.desc.GradientDescentOptimizer;
import solvers.Solver;
import solvers.regression.RegressionSolver;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;


/**
 * The type Linear regression solver.
 */
public class LinearRegressionSolver extends RegressionSolver {
    /**
     * Instantiates a new Linear regression solver.
     */
    public LinearRegressionSolver() {
        this.errorType= ErrorType.MSE;
    }

    @Override
    public Solver solve() {
        //assignTrainAndTest();
        DenseVector entryPoint = new DenseVector(new double[]{1,2,3,4}); //Statistics.getNormalDistributionSamples(numberOfVariables);
        model = gradientDescent(entryPoint);
        return this;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    //TODO needed to be pulled up
    public void predict(String opFilePath, boolean headers) throws Exception {
        double[][] testingX = getTestingX();
        double[] testingY = getTestingY();
        List<String> lines=new ArrayList<>();
        DenseMatrix denseMatrix=new DenseMatrix(testingX);
        for (int i = 0; i <testingX.length ; i++) {
            double actualVal= testingY[i];
            double predictedVal= model.predict(new DenseVector(testingX[i]));
            lines.add(Arrays.toString(testingX[i])+" ,"+actualVal+" "+predictedVal);
        }
        Path file = Paths.get(opFilePath);
        Files.write(file, lines, Charset.forName("UTF-8"));
    }

    //TODO :: needs to be moved to superclass?
    private Model gradientDescent(DenseVector entryPoint) {
        try {
            optimizer=new GradientDescentOptimizer(entryPoint,maxIterations,new DenseMatrix(trainingX),errorType,new DenseVector(trainingY),regularizer,regularizationCoefficient,learningRate,minDescentLimit);
            return  optimizer.optimize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
