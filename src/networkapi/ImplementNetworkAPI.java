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

//200 error codes come from here
public class ImplementNetworkAPI implements NetworkInterfaceAPI {
	@Override
	public List<Integer> respond(String input, String output, char delimiter ) {
		//build request inside of respond instead of taking UserRequest as parameter
		UserRequest request = buildRequest(input,output, delimiter); 
		// call the components
		ComputeComponent concept = new ImplementConceptAPI();
		
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
							System.err.println("E200:Could not compute value, skipping current index ${i}!");
							return 0;
						}
					}).
					collect(Collectors.toList());

			// after responses are created in list use as parameter in write request
			writeRequest(responses, request);
		} catch (Exception e) {
			System.err.println("E201: " + e.getMessage());
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
	@Override
	public List<String> readRequest(UserRequest request) {

	    ProcessorAPI storage = new ImplementProcessorAPI();

	    if (request == null) {
	        System.err.println("E200: Request is null");
	        return Collections.emptyList();
	    }

	    String filePath = request.getInputSource();
	    if (filePath == null || filePath.isEmpty()) {
	        System.err.println("E201: Input path invalid");
	        return Collections.emptyList();
	    }

	    try {
	        List<Integer> nums = storage.read(filePath);

	        return nums.stream()
	                   .map(String::valueOf)
	                   .collect(Collectors.toList());

	    } catch (Exception e) {
	        System.err.println("E204: Could not read file from input location!");
	        return Collections.emptyList();
	    }
	}

	public void writeRequest(List<Integer> results, UserRequest request) throws InvalidRequestException {
		ProcessorAPI storage = new ImplementProcessorAPI();
		if (request == null) {
			System.err.println("E205:The request is null or does not exist!");
			return;
		}
		String output = request.getOutputDestination(); // gets the output path from user request

		try {

			if (results == null ) {
				System.err.println("E206:Results is empty, computation was not performed!");
				return;
			}

			// check if given output location is there
			if (output == null || output.isEmpty()) {
				System.err.println("E207:Output location was not given in request!");
				return;
			}

			storage.write(output, results, request.getDelimiter()); // method writes converts results into String list
																	// and create a file using results
		} catch (Exception e) {
			System.err.println("E208:Error writing request to output" + e.getMessage());
			return;
		}
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
				System.err.println("E209:Could not be initialized as there was an error!");
				return Collections.emptyList();
			}

	
			for (int valueA : new ArrayList<>(values)) {
				responses.add(concept.computeValue(valueA));
			}
		} catch (Exception e) {
			System.err.println("E210: " + e.getMessage());
			return Collections.emptyList();
		}
		return responses;
	}

	// overloading method for testing harness
	public List<String> respond(List<String> values) {
	    if (values == null) {
	        System.err.println("E211:List was null!");
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
	            System.err.println("E212:Invalid number: '" + rawData + "', returning default 0");
	            responses.add("0");
	        } catch (Exception e) {
	            System.err.println("E213:Could not compute value for '" + rawData + "': " + e.getMessage());
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
