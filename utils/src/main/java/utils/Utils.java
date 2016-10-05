package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by abhishek on 29/9/16.
 */
public class Utils {


    /**
     * Write predictions.
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
