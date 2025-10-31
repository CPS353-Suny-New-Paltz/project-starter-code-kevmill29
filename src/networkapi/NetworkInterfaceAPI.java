package networkapi;

import assets.UserRequestCode;
import conceptapi.ComputeComponent;
import project.annotations.NetworkAPI;


@NetworkAPI
public interface NetworkInterfaceAPI {

	boolean initialize(UserRequestCode code);
	int respond(boolean isInit, int valueA, ComputeComponent concept);
}
