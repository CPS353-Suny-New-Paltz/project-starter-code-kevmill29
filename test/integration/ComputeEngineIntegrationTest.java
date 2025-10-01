package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import conceptapi.ComputeComponent;

import conceptapi.ImplementConceptAPI;

import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;


public class ComputeEngineIntegrationTest {
	@Test
	public void integrationTest() {
		MemoryTestInput input = new MemoryTestInput(Arrays.asList(1,10,25));
		MemoryTestOutput output = new MemoryTestOutput();
		ImplementDataStore datastore = new ImplementDataStore(input, output); //datastore implentation
		NetworkInterfaceAPI network = new ImplementNetworkAPI(); //networkapi implementation
		ComputeComponent computation = new ImplementConceptAPI(); //concept api implementation

		
		

		List<Integer>data = datastore.input();


		 // Process input in pairs
		    for (int i = 0; i < data.size() - 1; i++) {
		        List<Integer> pair = Arrays.asList(data.get(i), data.get(i + 1));
		        int result = computation.computeValues(pair);
		        output.write(Integer.toString(result));
		    }

		    // Expected values as strings
		    List<String> expectedValues = Arrays.asList(
		        Integer.toString(computation.computeValues(Arrays.asList(1, 10))),
		        Integer.toString(computation.computeValues(Arrays.asList(10, 25)))
		    );

		    List<String> actualValues = output.getOutput();

		    assertEquals(expectedValues, actualValues);

	}
}
