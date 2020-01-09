package util;

import linear.algebra.matrices.dense.DenseMatrix;
import util.constants.exception.LearningException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * The File utils class
 * for various fs-related
 * utility methods.
 */
public class FileUtils {

    private FileUtils() {

    }

    /**
     * Load data list.
     *
     * @param path the path
     * @return the list
     * @throws LearningException the io exception
     */
    public static Data loadData(String path, double testingDataPercent) {
        try (Stream<String> lines = Files.lines(Paths.get(new File(path).toURI()))) {
            return new Data(new DenseMatrix(lines
                    .map(line -> line.trim().split(","))
                    .map(word -> Stream.of(word).mapToDouble(Double::parseDouble).toArray())
                    .toArray(double[][]::new)), testingDataPercent);
        } catch (IOException e) {
            throw new LearningException(e);
        }
    }
}
