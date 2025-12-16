package assets;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class VizDataPrep {

    // Helper method that the Client can call directly
    public static void generateVizFile(String inputPath, String resultPath, String webDataPath) {
        System.out.println("\n--- VISUALIZATION UPDATE ---");
        System.out.println("Merging Input: " + inputPath);
        System.out.println("With Result:   " + resultPath);
        
        try {
            // 1. Read Inputs
            List<String> inputs = Files.readAllLines(Paths.get(inputPath)).stream()
                    .flatMap(line -> java.util.Arrays.stream(line.split(",")))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            // 2. Read Results
            List<String> results = Files.readAllLines(Paths.get(resultPath)).stream()
                    .flatMap(line -> java.util.Arrays.stream(line.split(",")))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            // 3. Write Pairs to Web Folder
            FileWriter writer = new FileWriter(webDataPath);
            int count = Math.min(inputs.size(), results.size());

            for (int i = 0; i < count; i++) {
                writer.write(inputs.get(i) + "," + results.get(i) + "\n");
            }
            writer.close();
            System.out.println("SUCCESS: Graph data updated at " + webDataPath);

        } catch (IOException e) {
            System.err.println("Viz Update Failed: " + e.getMessage());
        }
    }
}