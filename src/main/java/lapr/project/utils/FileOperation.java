package lapr.project.utils;

import lapr.project.model.Port;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOperation {

    public FileOperation() {
    }

    public static void writeToAFile(String path, StringBuilder data) throws IOException {
        try (PrintWriter pw = new PrintWriter(path)) {
            pw.println(data);
        }

    }

    public static void writeToAFileNearestPort(String path, Port nearestPort) throws IOException {
        try (PrintWriter pw = new PrintWriter(path)) {
            pw.println("Nearest port: " + nearestPort);
        }

    }

}
