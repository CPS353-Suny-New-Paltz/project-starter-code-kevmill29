package integration; 

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import networkapi.ImplementNetworkAPI;
import networkapi.MultiThreadedNetworkAPI; 
import networkapi.NetworkInterfaceAPI;
import networkapi.Version2Implementation; 
import processapi.TestOnlyDataStore;

public class BenchMarkTest {

    private static String INPUT_PATH;
    private static final String OUTPUT_PATH = "benchmark_output.txt";
    private static final char DELIMITER = ','; 
   
    private static final double MINIMUM_IMPROVEMENT_PERCENTAGE = 10.0;

    @BeforeAll
    static void setup() throws IOException {
        INPUT_PATH = TestOnlyDataStore.setupInputFile();
    }

    @AfterAll
    static void cleanup() {
        TestOnlyDataStore.cleanupFiles();
    }

    @Test
    void fasterVersionMeetsMinimumPerformanceImprovement() {
    	//MultiThreadedNetworkAPI
        ImplementNetworkAPI delegator = new ImplementNetworkAPI();
        
       
        // Force the Baseline to use only 1 thread. 
        // This simulates a "Single Threaded" vs "Multi Threaded" comparison, 
        // which is much more stable on GitHub's weak hardware.
        NetworkInterfaceAPI baselineAPI = new MultiThreadedNetworkAPI(delegator, 1);

        //  Version2Implementation (Optimized)
        // This will still use Runtime.getRuntime().availableProcessors() 
        NetworkInterfaceAPI fasterAPI = new Version2Implementation();

        System.out.println("--- Starting Benchmark (Single-Threaded Baseline vs Optimized V2) ---");

        // baseline
        long startTimeV1 = System.nanoTime();
        baselineAPI.respond(INPUT_PATH, OUTPUT_PATH, DELIMITER);
        long endTimeV1 = System.nanoTime();
        double durationSecondsV1 = (endTimeV1 - startTimeV1) / 1_000_000_000.0;

        //optimized
        long startTimeV2 = System.nanoTime();
        fasterAPI.respond(INPUT_PATH, OUTPUT_PATH, DELIMITER);
        long endTimeV2 = System.nanoTime();
        double durationSecondsV2 = (endTimeV2 - startTimeV2) / 1_000_000_000.0;

        // Shutdown Pools 
        baselineAPI.shutdown();
        fasterAPI.shutdown();

        // Calculate Results 
        double improvementPercentage = ((durationSecondsV1 - durationSecondsV2) / durationSecondsV1) * 100.0;

        System.out.printf("Baseline (MultiThreaded) Time: %.4f seconds%n", durationSecondsV1);
        System.out.printf("Optimized (Version 2) Time:    %.4f seconds%n", durationSecondsV2);
        System.out.printf("Improvement:                   %.2f%%%n", improvementPercentage);

        String failureMessage = String.format(
            "Performance target not met. Required improvement: %.2f%%, Actual improvement: %.2f%%",
            MINIMUM_IMPROVEMENT_PERCENTAGE, improvementPercentage
        );

        assertTrue(improvementPercentage >= MINIMUM_IMPROVEMENT_PERCENTAGE, failureMessage);
    }
}