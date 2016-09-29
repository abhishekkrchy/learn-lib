package models.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by abhishek on 29/9/16.
 */
public class CSVUtils {
    public static List<List<Double>> loadData(String path, boolean headerPresent) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String curr;
        if (headerPresent)
            bufferedReader.readLine();
        List<List<Double>> csvData = new ArrayList<>();
        while ((curr = bufferedReader.readLine()) != null) {
            List<Double> row = Arrays.stream(curr.split(",")).map(Double::parseDouble).collect(Collectors.toList());
            csvData.add(row);
        }
        return csvData;
    }
}
