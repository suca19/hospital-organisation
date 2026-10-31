package app.data;

import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeReader {

    public List<String> load(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            System.out.println("Read " + lines.size() + " lines");
            return lines;
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}
    