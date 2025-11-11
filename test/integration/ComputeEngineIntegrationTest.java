package integration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import assets.UserRequest;
import conceptapi.ComputeComponent;
import conceptapi.ImplementConceptAPI;
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
        //create a static code response for initialize method in userInterface
        UserRequest request = new UserRequest("here","outhere",',');
      
        NetworkInterfaceAPI userInterface = new ImplementNetworkAPI();
        boolean isInit = userInterface.initialize(request); //checks if the enum is giving a true response 
       ComputeComponent concept = new ImplementConceptAPI();
       	System.out.println(isInit);
        List<Integer>results = new ArrayList<>();
        

        // Simulate integration flow
        List<Integer> data = datastore.input();
        for (int i = 0; i < data.size(); i++) {
            int valueA = inputData.get(i);
            int result = userInterface.respond(isInit,valueA, concept); // if the enum is giving a true response it will use the implemented class conceptAPI method to calculate the answer
            //if the conceptAPI is working properly and the network API is started up properly then it will perform the calculation and then will be added to the results list
            results.add(result);
            output.write(Integer.toString(result));
        }

        // Expected output
        List<String> expected = new ArrayList<>();
        expected.add("204");
        expected.add("177");
        expected.add("174");
      

        assertEquals(expected, output.getOutput());
    }
    
    //test if exception handling is working 
  @Test
  public void shouldThrowTest() {
	  ImplementNetworkAPI testAPI = new ImplementNetworkAPI();
	  ComputeComponent testCompute = new ImplementConceptAPI();
	  List<Integer> data = Arrays.asList(1,10,25);
	  boolean isInit = false; // to force an error
	  List<Integer> results = testAPI.respond(isInit, testCompute, data);
	  //test 1
	  assertNotNull(results);
	  
	  assertTrue(results.isEmpty(), "Expected empty list instead of an exception being thrown");
	  
  }


   
}