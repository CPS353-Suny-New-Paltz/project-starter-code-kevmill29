package networkapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import assets.UserRequest;
import assets.UserRequestCode;
import conceptapi.ComputeComponent;
import processapi.ProcessorAPI;

public class ImplementNetworkAPI implements NetworkInterfaceAPI {
	@Override
	public List<Integer> respond(boolean isInit, int valueA, ComputeComponent concept, List<Integer>values) {
		
		//may not be necessary but using boolean to check if initialization has started if it has then call conceptAPi to start calculation
		int result = 0;
		List<Integer> responses = new ArrayList<>();
		if(isInit) {
			while(!values.isEmpty()) {
				valueA = values.remove(0);
				result =concept.computeValue(valueA);
				responses.add(result);
			}
			
		}else {
			//if false will cause integration test to purposefully fail by giving wrong numbers
			//could possibly add an exception instead if isInit is false or if the List is empty or null
			throw new IllegalArgumentException("User Request is invalid please try again!");
		}
return responses;
	}

	@Override
	public boolean initialize(UserRequest request) {
		return request.validation() == UserRequestCode.SUCCESS_RESPONSE;
	}
	
//this method will request the processorAPI to read through the userrequest input location
	public List<Integer> readRequest(ProcessorAPI storage, UserRequest request) {
		String filePath = request.getInputSource();
		List<Integer> values= new ArrayList<>();
		try {
			values = storage.read(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}
	
	public void writeRequest(ProcessorAPI storage, List<Integer> results, UserRequest request) throws IOException {
		String output = request.getOutputDestination(); //gets the output path from user request
		storage.write(output, results, request.getDelimiter()); // method writes converts results into String list and create a file using results
		
	}
	

	@Override //leave for integration test
	public int respond(boolean isInit, int valueA, ComputeComponent concept) {
		// TODO Auto-generated method stub
		return concept.computeValue(valueA);
	}




}
