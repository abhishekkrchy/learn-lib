//package solvers.regression.linear;
//
//import linear.algebra.util.constants.enums.ErrorType;
//import linear.algebra.vectors.dense.DenseVector;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//import solvers.regression.RegressionSolver;
//import util.constants.enums.OptimizerType;
//import util.constants.enums.Regularizer;
//
//
///**
// * The type Linear regression solver.
// */
//@Builder
////@NoArgsConstructor
//@AllArgsConstructor
//public class LinearRegressionSolver extends RegressionSolver {
//
//    /**
//     * Instantiates a new Linear regression solver.
//     */
//    // TODO : hard-coding of optimizertype
//
//
////    @Builder
////    private LinearRegressionSolver(String inputFile,OptimizerType optimizerType, ErrorType errorType, Regularizer regularizer, int maxIterations, double learningRate, double regularizationCoefficient, double minDescentLimit, double testingDataPercent) throws Exception {
//////        super(inputFile);
//////        this.errorType = errorType;
//////        this.optimizerType = optimizerType;
//////        this.regularizer = regularizer;
//////        this.maxIterations = maxIterations;
//////        this.learningRate = learningRate;
//////        this.regularizationCoefficient = regularizationCoefficient;
//////        this.minDescentLimit = minDescentLimit;
//////        this.testingDataPercent = testingDataPercent;
////        // TODO : hard-coded value
////        super(inputFile, errorType, optimizerType, regularizer, maxIterations, learningRate, regularizationCoefficient, minDescentLimit, testingDataPercent);
////    }
//    @Override
//    protected DenseVector entryPoint() {
//        return new DenseVector(new double[]{1, 2, 3, 4}); //Statistics.getNormalDistributionSamples(numberOfVariables);;
//    }
//
//
////    @Override
////    //TODO needed to be pulled up
////    public void predict(String opFilePath, boolean headers) throws Exception {
//    // TODO : predict add
////        double[][] testingX = getTestingX();
////        double[] testingY = getTestingY();
////        List<String> lines=new ArrayList<>();
////        DenseMatrix denseMatrix=new DenseMatrix(testingX);
////        for (int i = 0; i <testingX.length ; i++) {
////            double actualVal= testingY[i];
////            double predictedVal= model.predict(new DenseVector(testingX[i]));
////            lines.add(Arrays.toString(testingX[i])+" ,"+actualVal+" "+predictedVal);
////        }
////        Path file = Paths.get(opFilePath);
////        Files.write(file, lines, Charset.forName("UTF-8"));
//    //}
//}
