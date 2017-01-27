package linear.algebra.statistics;

import linear.algebra.vectors.dense.DenseVector;

import java.util.Random;

/**
 * The type Statistics for
 * various small statistical utilities.
 */
//TODO :: once done with machine-learning organize this module.
public class Statistics {
    /**
     * Gets normal distribution samples.
     *
     * @param numberOfSamples the number of samples
     * @return the normal distribution samples
     */
    public static DenseVector getNormalDistributionSamples(int numberOfSamples) {
        Random random = new Random(System.currentTimeMillis());
        double[] normalDistribution = new double[numberOfSamples];
        for (int i = 0; i < numberOfSamples; i++) {
            normalDistribution[i] = random.nextGaussian();
        }
        return new DenseVector(normalDistribution);
    }
}
