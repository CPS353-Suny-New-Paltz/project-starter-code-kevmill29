package networkapi;


import java.io.IOException;
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
		public List<Integer> prototype(NetworkInterfaceAPI api) {
			// Step 1: Build the user request
		UserRequest request = new UserRequest("input", "output", ";");
		List<Integer>results = new ArrayList<>();
		//Network API implementation
		api = new ImplementNetworkAPI();
		
		if(request.validation()== UserRequestCode.SUCCESS_RESPONSE) {
			//Call the storage component
			ProcessorAPI storage = new ImplementProcessorAPI();
			//Call the Computation component if true
			ComputeComponent computer = new ImplementConceptAPI();
			//this list contains the data from the inputsource by user
			List<Integer>data = api.readRequest(storage, request);
			//this list contains the computation results
			results = api.respond(api.initialize(request), 0, computer, data);
			//the respond method uses the init method to check if request is valid again to ensure validity
			// compute component performs computation and storage component reads through file in request to pass to computecomponent
			try {
				//writes results to new file in output location
				api.writeRequest(storage, results, request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(request.validation());
		return results;

			

			//Step 3: start initialization of process
			//this is where the processAPI will be implemented and called to start the processing of the request
		}

	}

