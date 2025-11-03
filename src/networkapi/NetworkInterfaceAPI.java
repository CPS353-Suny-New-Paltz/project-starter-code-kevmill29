package networkapi;

import java.util.List;

import assets.UserRequest;
import conceptapi.ComputeComponent;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface NetworkInterfaceAPI {
	
	boolean initialize(UserRequest request);
	void respond();
	
}

