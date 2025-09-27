package networkAPI;

import assets.userRequest;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface networkInterfaceAPI {

	void initalize(userRequest request);
	void respond();
}
