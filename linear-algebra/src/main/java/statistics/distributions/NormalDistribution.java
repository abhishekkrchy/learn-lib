package statistics.distributions;

import java.util.Random;

/**
 * Created by abhishek on 30/12/16.
 */
public class NormalDistribution implements Distribution{

     public double[] getNSmaples(int size) {
        Random random = new Random(System.currentTimeMillis());
        double[] normalDistribution = new double[size];
        for (int i = 0; i < size; i++) {
            normalDistribution[i] = random.nextGaussian();
        }
        return normalDistribution;
    }
}
