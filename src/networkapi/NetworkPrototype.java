package networkapi;


import java.io.IOException;

import assets.ResponseCode;
import assets.UserRequest;
import assets.UserRequestCode;
import project.annotations.NetworkAPIPrototype;

	public class NetworkPrototype {
	@NetworkAPIPrototype
		public void prototype(NetworkInterfaceAPI api) {
			// Step 1: Build the user request
		UserRequest request = null;

			try {
				 request = new UserRequest.Builder()
						.inputSource("input") // placeholder
						.outputDestination("output").
						delimiter(",") // or prompt user later
						.build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request = null;
			}



			// Step 2: Respond to user using enum
			//simulate response
			UserRequestCode code = request.validation();
			System.out.println(code);

			//Step 3: start initialization of process
			//this is where the processAPI will be implemented and called to start the processing of the request
		}

	}

