package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import conceptapi.ComputeComponent;
import conceptapi.ComputeValues;
import conceptapi.UserComponent;
import conceptapi.UserInterface;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import processapi.DataStore;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;

public class IntegrationTest {
	@Test
	public void integrationTest() {
		MemoryTestInput input = new MemoryTestInput(Arrays.asList(1,10,25));
		MemoryTestOutput output = new MemoryTestOutput();
		DataStore datastore = new ImplementDataStore(input, output);
		
		
		UserInterface userInput = new UserComponent();
		ComputeComponent computation = new ComputeValues();
		
		List<Integer>data = datastore.input();
		
		for(int i = 0; i<data.size();i++) {
			int num = data.get(i);
			int result= computation.computeValues(data);
			output.write(result);
			
		}
		List<Integer>expectedValues = Arrays.asList(0,0,0);
		List<Integer> actualValues = output.getOutput();
		
		
		//assertion
		assertEquals(expectedValues, actualValues);
	}
}
