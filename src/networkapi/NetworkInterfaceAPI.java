package networkapi;



import java.io.IOException;
import java.util.List;

import assets.InvalidRequestException;
import assets.UserRequest;
import conceptapi.ComputeComponent;
import processapi.ProcessorAPI;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface NetworkInterfaceAPI {
	List<Integer> respond(boolean isInit, ComputeComponent concept, List<Integer>values);
	boolean initialize(UserRequest request);
	List<Integer> readRequest(ProcessorAPI storage, UserRequest request) throws InvalidRequestException;
	int respond(boolean isInit, int valueA, ComputeComponent concept) ; //using for integration test
	void writeRequest(ProcessorAPI storage, List<Integer> results, UserRequest request) throws IOException, InvalidRequestException;
	
}

