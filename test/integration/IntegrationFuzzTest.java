package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import conceptapi.ComputeComponent;
import conceptapi.ImplementConceptAPI;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;

public class IntegrationFuzzTest {
	private static int NUM_TESTS_PER_RUN = 100;

	@Test
	public void integrationFuzzTest() {
		long seed = System.currentTimeMillis();
		Random random = new Random(seed);
		ImplementNetworkAPI testAPI = new ImplementNetworkAPI();
		ProcessorAPI storage = new ImplementProcessorAPI();
		ComputeComponent computer = new ImplementConceptAPI();
		runFuzzyTest(seed, random, testAPI,storage, computer);

	}

	private static void runFuzzyTest(long seed, Random random, ImplementNetworkAPI testAPI, ProcessorAPI storage,
			ComputeComponent computer) {
		System.out.println("Running test with seed " + seed);
		List<Integer> actual = new ArrayList<>();
		List<Integer>expected = new ArrayList<>();
		for (int i = 0; i < NUM_TESTS_PER_RUN; i++) {
			int a = random.nextInt(2001)-1000 ;//a cannot be greater than 1000 or less than -1000;
			expected.add(computer.computeValue(a)); // this uses the method directly inside of the conceptualapi implementation to compare to integrated api
			actual.add(a);	
		}
		//List will take the data and use the integrated api to create new list
		List<Integer> actualData = testAPI.respond(true, computer, actual);
		//check if the size is same
		assertEquals(expected.size(), actualData.size(), "Error: List size differs");

		for(int i = 0; i< expected.size(); i++) {
			assertEquals(expected.get(i), actualData.get(i), "Index mismatch or calculation error at: "+i);
		}
	}
	
	public static void main(String[]args) {
		if(args.length == 0) {
			System.out.println("Usage: Integration Fuzz Test <seed>");
			return; //so test does not crash early
		}
		long seed = Long.parseLong(args[0]);
		Random random = new Random(seed);
		System.out.println("Running test with seed: ${seed}");
		ImplementNetworkAPI testAPI = new ImplementNetworkAPI();
		ProcessorAPI storage = new ImplementProcessorAPI();
		ComputeComponent computer = new ImplementConceptAPI();
		runFuzzyTest(seed, random, testAPI,storage, computer);
	}
}
