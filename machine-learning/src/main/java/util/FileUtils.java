package util;

import linear.algebra.matrices.Matrix;
import linear.algebra.matrices.dense.DenseMatrix;

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
    /**
     * Load data list.
     *
     * @param path          the path
     * @param headerPresent the header present
     * @return the list
     * @throws IOException the io exception
     */
    public static Matrix loadData(String path, boolean headerPresent) throws IOException {
        return new DenseMatrix(Files.lines(Paths.get(new File(path).toURI()))
                .map(line ->line.trim().split(","))
                .map(word -> Stream.of(word).mapToDouble(Double::parseDouble).toArray())
                .toArray(double[][]::new));
    }
}
