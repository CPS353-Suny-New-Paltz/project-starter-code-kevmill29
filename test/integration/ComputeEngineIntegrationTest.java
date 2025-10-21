package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import conceptapi.ImplementConceptAPI;
import conceptapi.ComputeComponent;
import conceptapi.ComputeValues;
import conceptapi.UserComponent;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import processapi.ProcessorAPI;
import processapi.TestOutputCollector;

public class ComputeEngineIntegrationTest {

    @Test
    public void computeEngineIntegrationTest() {
        // Initial input: [1, 10, 25]
        List<Integer> inputData = Arrays.asList(1, 10, 25);
        TestOutputCollector output = new TestOutputCollector();

        // Test-only data store
        ProcessorAPI datastore = new TestDataStore(inputData, output);

        // Empty implementations
        NetworkInterfaceAPI userInterface = new ImplementNetworkAPI();
       
        ComputeComponent concept = new ImplementConceptAPI();

        // Get compute engine
        

        // Simulate integration flow
        List<Integer> data = datastore.input();
        for (int i = 0; i < data.size() - 1; i++) {
            int valueA = inputData.get(i);
            int result = concept.computeValues(valueA);
            output.write(Integer.toString(result));
        }

        // Expected output
        int expected = concept.computeValues(0)

        assertEquals(expected, output.getOutput());
    }

   
}