package networkapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import assets.InvalidRequestException;
import assets.UserRequest;
import assets.UserRequestCode;
import conceptapi.ComputeComponent;
import conceptapi.ImplementConceptAPI;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;

public class ImplementNetworkAPI implements NetworkInterfaceAPI {
	@Override
	public List<Integer> respond(String input, String output, char delimiter ) {
		//build request inside of respond instead of taking UserRequest as parameter
		UserRequest request = buildRequest(input,output, delimiter); 
		// call the components
		ComputeComponent concept = new ImplementConceptAPI();
		ProcessorAPI storage = new ImplementProcessorAPI();
		
		//validation check
		if(!initialize(request)) {
			System.err.println("User Request is invalid!");
			return Collections.emptyList();
		}
		// create list of empty list to respond with and list of values given by user
		// input
		List<Integer> responses;

		try {
			//Stream the integers in request and compute new responses
			 responses = readRequest(request).stream()
					.map(String::trim) //delete blank space inside of string
					.filter(s -> !s.isEmpty()) // iterate over list for strings
					.map(Integer::parseInt) // parse strings
					.map(i ->{  // run computations and return list with new computations
						try {
							return concept.computeValue(i);
						}catch (Exception e) {
							System.err.println("Could not compute value, skipping current index ${i}!");
							return 0;
						}
					}).
					collect(Collectors.toList());

			// after responses are created in list use as parameter in write request
			storage.write(output, responses, delimiter);
		} catch (Exception e) {
			System.err.println("Error!: " + e.getMessage());
			return Collections.emptyList();
		}

		return responses;
	}

	@Override
	public boolean initialize(UserRequest request) {
		if (request == null) {
			return false;
		}
		return request.validation() == UserRequestCode.SUCCESS_RESPONSE;
	}

//this method will request the processorAPI to read through the userrequest input location
	public List<String> readRequest(UserRequest request) {
		ImplementProcessorAPI storage = new ImplementProcessorAPI();
		List<String> values = new ArrayList<>();
		// check if storage component is initialized
		try {
			if (request == null) {
				System.err.println("Request is null! Please try again!");
				return Collections.emptyList();			
				}

			// check if request input path is valid
			String filePath = request.getInputSource();
			if (filePath == null || filePath.isEmpty()) {
				System.err.println("File path does not exist!");
				return Collections.emptyList();
			}

			values = storage.read(values, filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Could not read file from input location!");
			return Collections.emptyList();
		}
		return values;
	}



	@Override // leave for integration test
	public int respond(boolean isInit, int valueA, ComputeComponent concept) {
		// TODO Auto-generated method stub
		return concept.computeValue(valueA);
	}

	// this is for fuzz testing purposes as it does not take in a built request, a
	// null request will return an empty list due to the
	public List<Integer> respond(boolean isInit,  List<Integer> values) {
		List<Integer> responses = new ArrayList<>();
		ComputeComponent concept = new ImplementConceptAPI();
		try {
			if (!isInit || values == null) {
				System.err.println("Could not be initialized as there was an error!");
				return Collections.emptyList();
			}

	
			for (int valueA : new ArrayList<>(values)) {
				responses.add(concept.computeValue(valueA));
			}
		} catch (Exception e) {
			System.err.println("Error!: " + e.getMessage());
			return Collections.emptyList();
		}
		return responses;
	}

	// overloading method for testing harness
	public List<String> respond(List<String> values) {
	    if (values == null) {
	        System.err.println("List was null!");
	        return Collections.emptyList();
	    }

	    ImplementProcessorAPI storage = new ImplementProcessorAPI();
	    ComputeComponent concept = new ImplementConceptAPI();

	    List<String> responses = new ArrayList<>();

	    for (String rawData : values) {
	        if (rawData == null) {
	            
	            responses.add("0");
	            continue;
	        }

	        String trimmed = rawData.trim();

	        if (trimmed.isEmpty()) {
	            
	            responses.add("0");
	            continue;
	        }

	        try {
	            int input = Integer.parseInt(trimmed);   // may throw
	            int result = concept.computeValue(input); // may throw

	         
	            // storage.writeResult(result);

	            responses.add(String.valueOf(result));
	        } catch (NumberFormatException e) {
	            System.err.println("Invalid number: '" + rawData + "', returning default 0");
	            responses.add("0");
	        } catch (Exception e) {
	            System.err.println("Could not compute value for '" + rawData + "': " + e.getMessage());
	            responses.add("0");
	        }
	    }

	    return responses;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	public UserRequest buildRequest(String input, String output, char delimiter) {
		return new UserRequest(input, output, delimiter);
	}

	

}
