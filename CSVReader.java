import java.io.*;
import java.util.*;

public class CSVReader {

    public static List<String[]> readCSV(String fileName) throws IOException {
        List<String[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            data.add(line.split(","));
        }
        br.close();
        return data;
    }
}
