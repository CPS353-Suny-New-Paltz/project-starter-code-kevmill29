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
		List<Integer> responses = new ArrayList<>();
		try {
	    if (!isInit) {
	        throw new IllegalArgumentException("User Request is invalid, please try again!");
	    }
	    
	    if(concept == null) {
	    	throw new IllegalArgumentException("Compute component is missing!");
	    }
	    
	    if(values == null || values.isEmpty()) {
	    	throw new InvalidRequestException("Provided values does not exist!");
	    }

		
		
		for (int valueA : new ArrayList<>(values)) {
    	responses.add(concept.computeValue(valueA));
		}
		}catch(Exception e) {
			throw new IllegalArgumentException();
		}
		return responses;
	}

	@Override
	public boolean initialize(UserRequest request) {
		return request.validation() == UserRequestCode.SUCCESS_RESPONSE;
	}
	
//this method will request the processorAPI to read through the userrequest input location
	public List<Integer> readRequest(ProcessorAPI storage, UserRequest request){
		//check if storage component is initialized
		if(storage == null) {
			throw new IllegalArgumentException("Storage is empty or null!");
		}
		
		//check if request input path is valid
		String filePath = request.getInputSource();
		if(filePath == null || filePath.isEmpty()) {
			throw new InvalidRequestException("This request is invalid or empty.");
		}
		List<Integer> values= new ArrayList<>();
		try {
			values = storage.read(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Could not read file from input location!");
			throw new RuntimeException();
		}
		return values;
	}
	
	public void writeRequest(ProcessorAPI storage, List<Integer> results, UserRequest request) {
		String output = request.getOutputDestination(); //gets the output path from user request
		
		if(results == null || results.isEmpty()) {
			throw new InvalidRequestException("Results is empty or null!");
		}
		
		//check if storage component is init
		if(storage == null) {
			throw new IllegalArgumentException("Storage is empty or null!");
		}
		
		//check if given output location is there
		if(output == null || output.isEmpty()) {
			throw new InvalidRequestException("Output location is invalid or null!");
		}
		
		try {
		storage.write(output, results, request.getDelimiter()); // method writes converts results into String list and create a file using results
		}catch(Exception e) {
			System.err.println("Error writing request to output"+e.getMessage());
			throw new RuntimeException("Failed to write to output");
		}
	}
	

	@Override //leave for integration test
	public int respond(boolean isInit, int valueA, ComputeComponent concept) {
		// TODO Auto-generated method stub
		return concept.computeValue(valueA);
	}




}


