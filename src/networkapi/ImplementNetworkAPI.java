package networkapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
	        throw new InvalidRequestException("User Request is invalid, please try again!");
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
			System.err.println("Error!: "+e.getMessage());
			return Collections.emptyList();
		}
		return responses;
	}

	@Override
	public boolean initialize(UserRequest request) {
		if(request == null ) {
			return false;
		}
		return request.validation() == UserRequestCode.SUCCESS_RESPONSE;
	}
	
//this method will request the processorAPI to read through the userrequest input location
	public List<Integer> readRequest(ProcessorAPI storage, UserRequest request){
		List<Integer> values= new ArrayList<>();
		//check if storage component is initialized
		try {
		if(request == null) {
			throw new InvalidRequestException("Request is invalid please try again!");
		}
		if(storage == null) {
			throw new IllegalArgumentException("Storage is empty or null!");
		}
		
		//check if request input path is valid
		String filePath = request.getInputSource();
		if(filePath == null || filePath.isEmpty()) {
			throw new InvalidRequestException("This request is invalid or empty.");
		}
	
		
			values = storage.read(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Could not read file from input location!");
			return Collections.emptyList();
		}
		return values;
	}
	
	public void writeRequest(ProcessorAPI storage, List<Integer> results, UserRequest request) throws InvalidRequestException {
		if(request == null) {
			throw new InvalidRequestException("Request is invalid, request cannot be invalid!");
		}
		String output = request.getOutputDestination(); //gets the output path from user request

		try {
			
	
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
		
		
		
		storage.write(output, results, request.getDelimiter()); // method writes converts results into String list and create a file using results
		}catch(Exception e) {
			System.err.println("Error writing request to output"+e.getMessage());
		}
	}
	

	@Override //leave for integration test
	public int respond(boolean isInit, int valueA, ComputeComponent concept) {
		// TODO Auto-generated method stub
		return concept.computeValue(valueA);
	}




}


