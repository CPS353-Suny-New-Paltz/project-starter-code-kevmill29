package main.networkapi;

import main.assets.UserRequest;
import main.project.annotations.NetworkAPI;


@NetworkAPI
public interface NetworkInterfaceAPI {

	void initalize(UserRequest request);
	void respond();
}
