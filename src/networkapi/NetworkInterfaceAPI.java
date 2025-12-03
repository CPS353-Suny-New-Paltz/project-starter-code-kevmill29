package networkapi;



import java.util.List;

import assets.UserRequest;
import conceptapi.ComputeComponent;
import processapi.ProcessorAPI;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface NetworkInterfaceAPI {
	List<Integer> respond(String input, String output, char delimiter);
	boolean initialize(UserRequest request);
	List<String> readRequest( UserRequest request);
	int respond(boolean isInit, int valueA, ComputeComponent concept) ; //using for integration test
	void shutdown();
	UserRequest buildRequest(String input, String output,char delimiter);
	void writeRequest(List<Integer> newData, UserRequest request);


}


