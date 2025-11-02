package smoketest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import assets.UserRequest;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import networkapi.NetworkPrototype;

public class TestNetworkInterfaceAPI {
		@Test
		public void smokeTestFail() {
		    
		    NetworkInterfaceAPI realAPI = new ImplementNetworkAPI();
		   
		    UserRequest request = new UserRequest("here",null,",");

		 
		    NetworkPrototype prototype = new NetworkPrototype();
		    prototype.prototype(realAPI);
		   assertNotEquals(true, realAPI.initialize(request)); 
		   // now there is an implementation and if any part of the request is null it should return a false value for init
		   //there have been instances in the past when I ran the validation and even though a part of the request was null it still has returned true
		}
		@Test
		public void smokeTestPass() {
			  NetworkInterfaceAPI realAPI = new ImplementNetworkAPI();

			    UserRequest request = new UserRequest("here","outhere",",");

			   
		   

			   
			    NetworkPrototype prototype = new NetworkPrototype();
			    prototype.prototype(realAPI);
			   assertEquals(true, realAPI.initialize(request));
		}

}

