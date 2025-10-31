package smoketest;


import static org.junit.jupiter.api.Assertions.assertEquals;



import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;


import assets.UserRequest;

import assets.UserRequestCode;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import networkapi.NetworkPrototype;

public class TestNetworkInterfaceAPI {
		@Test
		public void smokeTestFail() {
		    
		    NetworkInterfaceAPI realAPI = new ImplementNetworkAPI();
		    UserRequestCode code = UserRequestCode.SUCCESS_RESPONSE; //create a success response
	

		 
		    NetworkPrototype prototype = new NetworkPrototype();
		    prototype.prototype(realAPI);
		   assertEquals(true, realAPI.initialize(code)); // since there is no actual implementation initialize should be false 
		}
		@Test
		public void smokeTestPass() {
			  NetworkInterfaceAPI realAPI = new ImplementNetworkAPI();
			    UserRequestCode code = UserRequestCode.SUCCESS_RESPONSE;
			   
		   

			   
			    NetworkPrototype prototype = new NetworkPrototype();
			    prototype.prototype(realAPI);
			   assertEquals(false, realAPI.initialize(code));
		}

}

