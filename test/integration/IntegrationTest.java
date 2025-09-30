package integration;
import processapi.ProcessorAPI;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import conceptapi.ComputeComponent;
import conceptapi.ComputeValues;
import conceptapi.UserComponent;
import conceptapi.UserInterface;
import processapi.DataStore;

public class IntegrationTest {
	@Test
	public void integrationTest() {
		MemoryTestInput input = new MemoryTestInput(Arrays.asList(1,10,25));
		MemoryTestOutput output = new MemoryTestOutput();
		ProcessorAPI datastore = new ImplementDataStore(input, output);


		UserInterface userInput = new UserComponent();
		ComputeComponent computation = new ComputeValues();

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
