package solvers.regression.linear;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.statistics.Statistics;
import linear.algebra.util.Vectors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import lombok.Builder;
import models.Model;
import optimizer.Optimizers;
import optimizer.grad.desc.GradientDescentOptimizer;
import solvers.Solver;
import solvers.regression.RegressionSolver;
import util.constants.enums.OptimizerType;
import util.constants.enums.Regularizer;

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

import static util.constants.enums.OptimizerType.GRADIENT_DESCENT;
import static util.constants.enums.Regularizer.NONE;


/**
 * The type Linear regression solver.
 */
public class LinearRegressionSolver extends RegressionSolver {

    /**
     * Instantiates a new Linear regression solver.
     */
    // TODO : hard-codin of optimizertype
    @Builder
    public LinearRegressionSolver(String inputFile, ErrorType errorType, Regularizer regularizer, int maxIterations, double learningRate, double regularizationCoefficient, double minDescentLimit, double testingDataPercent) throws Exception {
        super(inputFile, errorType, GRADIENT_DESCENT, regularizer, maxIterations, learningRate, regularizationCoefficient, minDescentLimit, testingDataPercent);
    }

    @Override
    public Solver solve() {
        // TODO : test hard-code
        DenseVector entryPoint = new DenseVector(new double[]{1, 2, 3, 4}); //Statistics.getNormalDistributionSamples(numberOfVariables);
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
        // TODO : predict add
//        double[][] testingX = getTestingX();
//        double[] testingY = getTestingY();
//        List<String> lines=new ArrayList<>();
//        DenseMatrix denseMatrix=new DenseMatrix(testingX);
//        for (int i = 0; i <testingX.length ; i++) {
//            double actualVal= testingY[i];
//            double predictedVal= model.predict(new DenseVector(testingX[i]));
//            lines.add(Arrays.toString(testingX[i])+" ,"+actualVal+" "+predictedVal);
//        }
//        Path file = Paths.get(opFilePath);
//        Files.write(file, lines, Charset.forName("UTF-8"));
    }

    //TODO :: needs to be moved to superclass?
    private Model gradientDescent(DenseVector entryPoint) {
        optimizer = Optimizers.optimizer(optimizerType, entryPoint, maxIterations, trainingX, errorType, trainingY, regularizer, regularizationCoefficient, learningRate, minDescentLimit);
        return optimizer.optimize();
    }
}
