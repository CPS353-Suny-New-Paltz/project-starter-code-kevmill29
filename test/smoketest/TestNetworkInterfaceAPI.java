package smoketest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import assets.UserRequest;
import assets.UserRequest.Builder;
import assets.UserRequestCode;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import networkapi.NetworkPrototype;

public class TestNetworkInterfaceAPI {
		@Test
		public void smokeTest() {
		    // Explicit constructor call for Spoon to detect
		    NetworkInterfaceAPI realAPI = new ImplementNetworkAPI();
		    UserRequestCode code = UserRequestCode.SUCCESS_RESPONSE;
		   
//		    UserRequest mockRequest = mock(UserRequest.class);
//		    when(mockRequest.validation()).thenReturn(code);

		    // Proceed with your test logic
		    NetworkPrototype prototype = new NetworkPrototype();
		    assertThrows(NullPointerException.class, ()-> {
		    	prototype.prototype(realAPI);
		    });
		}

}

