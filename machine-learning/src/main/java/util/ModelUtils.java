package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The ModelUtils class,
 * for utility methods
 * related to both fs and
 * models.
 * TODO :: can be moved to fileUtils or fine?
 */
public class ModelUtils {
    /**
     * Write predictions to file.
     *
     * @param predictions the predictions
     * @param outpath     the outpath
     * @throws IOException the io exception
     */
    public static void writePredictions(List<Double> predictions, String outpath) throws IOException {
        FileWriter fileWriter = new FileWriter(outpath);
        for (double prediction : predictions) {
            fileWriter.write(prediction + "\n");
        }
        fileWriter.close();
    }
}
