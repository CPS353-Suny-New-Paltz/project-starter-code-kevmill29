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
	public List<Integer> respond(boolean isInit, UserRequest request) {
		if (request == null || !isInit) {
			System.err.println("Invalid request or component no initialized!");
			return Collections.emptyList();
		}
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
							System.err.println("Could not compute value, skipping current index ${i}!");
							return 0;
						}
					}).
					collect(Collectors.toList());

			// after responses are created in list use as parameter in write request
			writeRequest(responses, request);
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
				return Collections.emptyList();			}

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

	public void writeRequest(List<Integer> results, UserRequest request) throws InvalidRequestException {
		ProcessorAPI storage = new ImplementProcessorAPI();
		if (request == null) {
			System.err.println("The request is null or does not exist!");
			return;
		}
		String output = request.getOutputDestination(); // gets the output path from user request

		try {

			if (results == null || results.isEmpty()) {
				System.err.println("Results is empty, computation was not performed!");
				return;
			}

			// check if given output location is there
			if (output == null || output.isEmpty()) {
				System.err.println("Output location was not given in request!");
				return;
			}

			storage.write(output, results, request.getDelimiter()); // method writes converts results into String list
																	// and create a file using results
		} catch (Exception e) {
			System.err.println("Error writing request to output" + e.getMessage());
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
	public List<Integer> respond(boolean isInit, ComputeComponent concept, List<Integer> values) {
		List<Integer> responses = new ArrayList<>();
		try {
			if (!isInit || values == null) {
				System.err.println("Could not be initialized as there was an error!");
				return Collections.emptyList();
			}
		if (concept == null) {
				System.err.println("Computation component was not initialized!");
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
	public List<String> respond(List<String> values, UserRequest request) {
		if (values == null || values.isEmpty()) {
			System.err.println("No values were given or list was null!");
			return Collections.emptyList();
		}
		ImplementProcessorAPI storage = new ImplementProcessorAPI();
		ComputeComponent concept = new ImplementConceptAPI();
		List<String> responses = readRequest(request).stream()
			    .map(String::trim)
			    .filter(s -> !s.isEmpty())
			    .map(Integer::parseInt)
			    .map(i -> {
			        try {
			            int result = concept.computeValue(i);
			            return String.valueOf(result);
			        } catch (Exception e) {
			            System.err.println("Could not compute value, skipping index " + i);
			            return "0";
			        }
			    })
			    .collect(Collectors.toList());
		try {

		} catch (InvalidRequestException e) {
			System.err.println("Error!: " + e.getMessage());
			return Collections.emptyList();
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
