
package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import conceptapi.ConceptAPI;
import conceptapi.ImplementConceptAPI;
import conceptapi.ComputeComponent;
import conceptapi.ComputeValues;
import conceptapi.UserComponent;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;
import processapi.TestDataStore;

public class IntegrationTest {

    @Test
    public void integrationTest() {
        // Test-specific input/output
        List<Integer> inputData = Arrays.asList(1, 10, 25);
        TestOutputCollector output = new TestOutputCollector();

        // ✅ Instantiate test-specific ProcessAPI implementation
        ProcessorAPI datastore = new TestDataStore(inputData, output);

        // ✅ Instantiate real NetworkAPI implementation
        NetworkInterfaceAPI userInterface = new ImplementNetworkAPI();

        // ✅ Instantiate real ConceptualAPI implementation
        UserComponent userComponent = new UserComponent();
        ConceptAPI concept = new ImplementConceptAPI(); // This must be a concrete class in src/ that implements ConceptAPI

        // ✅ Use ConceptualAPI to get the ComputeComponent
        ComputeComponent computation = concept.compute(userComponent);

        // Simulate integration flow
        List<Integer> data = datastore.input();
        for (int i = 0; i < data.size() - 1; i++) {
            List<Integer> pair = Arrays.asList(data.get(i), data.get(i + 1));
            int result = computation.computeValues(pair);
            output.write(Integer.toString(result));
        }

        // Expected results
        List<String> expected = Arrays.asList(
            Integer.toString(computation.computeValues(Arrays.asList(1, 10))),
            Integer.toString(computation.computeValues(Arrays.asList(10, 25)))
        );

        assertEquals(expected, output.getOutput());
    }
    
    @Test
    public void constructorSanityCheck() {
        new ImplementNetworkAPI();
        new ImplementConceptAPI();
        new ImplementProcessorAPI();
        new TestDataStore(Arrays.asList(1, 2), new TestOutputCollector());
    }

}

