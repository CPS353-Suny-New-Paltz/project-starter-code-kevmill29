package networkapi;

import java.io.IOException;
import java.util.List;
import java.util.Queue;

import assets.UserRequest;
import assets.UserRequestCode;
import concept.ComputeComponent;
import process.ProcessorAPI;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface NetworkInterfaceAPI {
	
	boolean initialize(UserRequest request);
	List<Integer>respond(boolean isInit, int valueA, ComputeComponent concept, List<Integer>values);
	int respond(boolean isInit, int valueA, ComputeComponent concept);
	List<Integer>readRequest(ProcessorAPI storage, UserRequest request);
	void writeRequest(ProcessorAPI storage,List<Integer> results, UserRequest request) throws IOException;
}

