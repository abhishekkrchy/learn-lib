package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ModelUtils {
    public static void writePredictions(List<Double> predictions, String outpath) throws IOException {
        FileWriter fileWriter = new FileWriter(outpath);
        for (double prediction : predictions) {
            fileWriter.write(prediction + "\n");
        }
        fileWriter.close();
    }
}
