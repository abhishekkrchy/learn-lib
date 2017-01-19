package linear.algebra.statistics;

import linear.algebra.vectors.dense.DenseVector;

import java.util.Random;

public class Statistics {
    public static DenseVector getNormalDistributionSamples(int numberOfSamples) {
        Random random = new Random(System.currentTimeMillis());
        double[] normalDistribution = new double[numberOfSamples];
        for (int i = 0; i < numberOfSamples; i++) {
            normalDistribution[i] = random.nextGaussian();
        }
        return new DenseVector(normalDistribution);
    }
}
