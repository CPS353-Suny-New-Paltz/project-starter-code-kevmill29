package networkapi;


import java.util.ArrayList;
import java.util.List;

import assets.UserRequest;
import assets.UserRequestCode;
import conceptapi.ComputeComponent;
import conceptapi.ImplementConceptAPI;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;
import project.annotations.NetworkAPIPrototype;

	public class NetworkPrototype {
	
		@NetworkAPIPrototype
	
		public void prototype(NetworkInterfaceAPI api) {
			// Step 1: Build the user request
		UserRequest request = new UserRequest("input", "output", ";");
		List<Integer>results = new ArrayList<>();
		//Network API implementation
		api = new ImplementNetworkAPI();
		try {
		if(request.validation()== UserRequestCode.SUCCESS_RESPONSE) {
			//Call the storage component
			ProcessorAPI storage = new ImplementProcessorAPI();
			//Call the Computation component if true
			ComputeComponent computer = new ImplementConceptAPI();
			//this list contains the data from the inputsource by user
			
			//this list contains the computation results
			
			//the respond method uses the init method to check if request is valid again to ensure validity
			// compute component performs computation and storage component reads through file in request to pass to computecomponent
		
		}
		}catch(Exception e) {
			System.err.println("Could not complete process please try again!");
			throw new RuntimeException("Process failed!"+ e.getMessage());
		}
		System.out.println(request.validation());

			

			//Step 3: start initialization of process
			//this is where the processAPI will be implemented and called to start the processing of the request
		}

	}

