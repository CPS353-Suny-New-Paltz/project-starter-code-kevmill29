package networkAPI;


import assets.responseCode;
import assets.userRequest;
import project.annotations.NetworkAPIPrototype;

	public class networkPrototype {
		@NetworkAPIPrototype
		public void prototype(networkInterfaceAPI api) {
			// Step 1: Build the user request
			
			userRequest request = new userRequest.Builder()
					.inputSource("user") // placeholder
					.outputDestination("src/Output/output.txt").delimiter(",") // or prompt user later
					.useDefaultDelimiter(true).build();

			
			
			// Step 2: Respond to user using enum
			//simulate response 
			if (request.isValid()) {
				System.out.println("Request completed successfully.");
				responseCode response = responseCode.SUCCESS; 
				//if request successful we will call process api
			} else {
				System.out.println("Request failed.");
				responseCode response = responseCode.FAILURE; 
			}
			
			//Step 3: start initialization of process
			//this is where the processAPI will be implemented and called to start the processing of the request
		}
		//this method will check if the request is valid and if not will send a failure signal
	
	}

