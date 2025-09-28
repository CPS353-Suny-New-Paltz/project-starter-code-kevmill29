package integration;

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
		DataStore datastore = new ImplementDataStore(input, output);


		UserInterface userInput = new UserComponent();
		ComputeComponent computation = new ComputeValues();

		List<Integer>data = datastore.input();

		for (Integer num : data) {
			int result= computation.computeValues(data);
			output.write(result);

		}
		List<Integer>expectedValues = Arrays.asList(0,0,0);
		List<Integer> actualValues = output.getOutput();


		//assertion
		assertEquals(expectedValues, actualValues);
	}
}
