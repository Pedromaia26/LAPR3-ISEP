package lapr.project.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {

    public static void writeToAFile(String path, String data) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);

        try {
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fw.close();
        }

    }
}
