package processapi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestOnlyDataStore {
	private static final String INPUT_FILE_PATH = "benchmark_input.txt";
    private static final int DATA_SIZE = 400_000; // 400k data points

   //creates a large seed file for testing
    public static String setupInputFile() throws IOException {
        File inputFile = new File(INPUT_FILE_PATH);
        try (FileWriter writer = new FileWriter(inputFile)) {
            // Write 1 million simple integers (or complex ones, depending on computeValue)
            for (int i = 0; i < DATA_SIZE; i++) {
                writer.write(String.valueOf(i % 100)); // Use simple numbers to emphasize CPU time
                writer.write(i == DATA_SIZE - 1 ? "" : "\n"); // Use newline delimiter
            }
        }
        return INPUT_FILE_PATH;
    }

//used to clean up files after large number of files are created
    public static void cleanupFiles() {
        new File(INPUT_FILE_PATH).delete();
        new File("benchmark_output.txt").delete();
    }
}
