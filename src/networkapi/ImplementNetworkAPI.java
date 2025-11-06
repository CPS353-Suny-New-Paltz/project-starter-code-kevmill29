package networkapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import assets.InvalidRequestException;
import assets.UserRequest;
import assets.UserRequestCode;
import conceptapi.ComputeComponent;
import processapi.ProcessorAPI;

public class ImplementNetworkAPI implements NetworkInterfaceAPI {
	@Override
	public List<Integer> respond(boolean isInit, ComputeComponent concept, List<Integer> values) {
	    if (!isInit) {
	        throw new IllegalArgumentException("User Request is invalid, please try again!");
	    }

		List<Integer> responses = new ArrayList<>();
		
		for (int valueA : new ArrayList<>(values)) {
    	responses.add(concept.computeValue(valueA));
		}
		return responses;
	}

	@Override
	public boolean initialize(UserRequest request) {
		return request.validation() == UserRequestCode.SUCCESS_RESPONSE;
	}
	
//this method will request the processorAPI to read through the userrequest input location
	public List<Integer> readRequest(ProcessorAPI storage, UserRequest request) throws InvalidRequestException {
		
		String filePath = request.getInputSource();
		if(filePath == null || filePath.isEmpty()) {
			throw new InvalidRequestException("This request is invalid or empty.");
		}
		List<Integer> values= new ArrayList<>();
		try {
			values = storage.read(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}
	
	public void writeRequest(ProcessorAPI storage, List<Integer> results, UserRequest request) throws IOException, InvalidRequestException {
		String output = request.getOutputDestination(); //gets the output path from user request
		if(output == null || output.isEmpty()) {
			throw new InvalidRequestException("Output location is invalid or null!");
		}
		storage.write(output, results, request.getDelimiter()); // method writes converts results into String list and create a file using results
		
	}
	

	@Override //leave for integration test
	public int respond(boolean isInit, int valueA, ComputeComponent concept) {
		// TODO Auto-generated method stub
		return concept.computeValue(valueA);
	}




}