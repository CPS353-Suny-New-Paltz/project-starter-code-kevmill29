package integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import networkapi.Version2Implementation;
import processapi.TestOnlyDataStore;

public class BenchMarkTest {

    private static String INPUT_PATH;
    private static final String OUTPUT_PATH = "benchmark_output.txt";
    private static final char DELIMITER = ','; // Assuming the component uses a delimiter

    private static final double MINIMUM_IMPROVEMENT_PERCENTAGE = 10.0;



    @BeforeAll
    static void setup() throws IOException {
        // Create the large, consistent dataset for both runs
        INPUT_PATH = TestOnlyDataStore.setupInputFile();
    }

    @AfterAll
    static void cleanup() {
        // Ensure temporary files are deleted after the test runs
        TestOnlyDataStore.cleanupFiles();
    }

    // --- The Core Benchmark Test ---

    @Test
    void fasterVersionMeetsMinimumPerformanceImprovement() {
        //  Setup both versions of the API
        NetworkInterfaceAPI originalAPI = new ImplementNetworkAPI();
        NetworkInterfaceAPI fasterAPI = new Version2Implementation();

        //  Measure Original (V1) Performance
        System.out.println("--- Starting Benchmark (V1 vs V2) ---");
        long startTimeV1 = System.nanoTime();
        
        //  running the full pipeline
        originalAPI.respond(INPUT_PATH, OUTPUT_PATH, DELIMITER);
        
        long endTimeV1 = System.nanoTime();
        double durationSecondsV1 = (endTimeV1 - startTimeV1) / 1_000_000_000.0; // since i'm using nanoseconds divide by 1 billion to get the time in seconds. 1 second == 1 billion nanoseconds
        
        //  Measure Faster (V2) Performance
        long startTimeV2 = System.nanoTime();
        // running the full pipeline (V2)
        fasterAPI.respond(INPUT_PATH, OUTPUT_PATH, DELIMITER);
        
        long endTimeV2 = System.nanoTime();
        double durationSecondsV2 = (endTimeV2 - startTimeV2) / 1_000_000_000.0;
        
      
        
       
        // Calculate percentage improvement: ((V1 Time - V2 Time) / V1 Time) * 100
        double improvementPercentage = ((durationSecondsV1 - durationSecondsV2) / durationSecondsV1) * 100.0;
        
        System.out.printf("Original (V1) Time: %.3f seconds%n", durationSecondsV1);
        System.out.printf("Faster (V2) Time:   %.3f seconds%n", durationSecondsV2);
        System.out.printf("Improvement:        %.2f%%%n", improvementPercentage);
        
        //In case new version is not an improvement report the failure
        String failureMessage = String.format(
            "Performance target not met. Required improvement: %.2f%%, Actual improvement: %.2f%%",
            MINIMUM_IMPROVEMENT_PERCENTAGE, improvementPercentage
        );
        
        assertTrue(improvementPercentage >= MINIMUM_IMPROVEMENT_PERCENTAGE, failureMessage);
    }
}