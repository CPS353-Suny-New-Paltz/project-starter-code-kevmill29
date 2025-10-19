package networkapi;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assets.RequestResponseCode;
import assets.ResponseCode;
import assets.UserRequest;
import project.annotations.NetworkAPIPrototype;

	public class NetworkPrototype {
		@NetworkAPIPrototype
		public void prototype(NetworkInterfaceAPI api) {
			// Step 1: Build the user request
			
		
			UserRequest request = null;
			try {
				request = new UserRequest.Builder()
						.inputSource() // placeholder
						.outputDestination().delimiter() // or prompt user later
						.build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean validation = request.isValid();
			RequestResponseCode code = request.secondValidation();

			// Step 2: Respond to user using enum
			//simulate response
			if(validation && code== code.SUCCESSRESPONSE) {
				System.out.println(code.SUCCESSRESPONSE);
			}else {
				System.out.println(code);
			}
			

			//Step 3: start initialization of process
			//this is where the processAPI will be implemented and called to start the processing of the request
		}

	}

