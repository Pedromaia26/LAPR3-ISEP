package lapr.project.utils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileOperationTest {

    @Test
    public void writeToAFile() throws IOException {
        String file = "ficheiro";
        String data = "informação";
        FileOperation.writeToAFile(file, data);
    }
}