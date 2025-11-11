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
		if (request == null) {
			throw new InvalidRequestException("The request cannot be null!");
		}
		// call the components
		ComputeComponent concept = new ImplementConceptAPI();

		// create list of empty list to respond with and list of values given by user
		// input
		List<Integer> responses;

		try {
			if (!isInit) {
				throw new InvalidRequestException("User Request is invalid, please try again!");
			}
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
				throw new InvalidRequestException("Request is invalid please try again!");
			}

			// check if request input path is valid
			String filePath = request.getInputSource();
			if (filePath == null || filePath.isEmpty()) {
				throw new InvalidRequestException("This request is invalid or empty.");
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
			throw new InvalidRequestException("Request is invalid, request cannot be invalid!");
		}
		String output = request.getOutputDestination(); // gets the output path from user request

		try {

			if (results == null || results.isEmpty()) {
				throw new InvalidRequestException("Results is empty or null!");
			}

			// check if given output location is there
			if (output == null || output.isEmpty()) {
				throw new InvalidRequestException("Output location is invalid or null!");
			}

			storage.write(output, results, request.getDelimiter()); // method writes converts results into String list
																	// and create a file using results
		} catch (Exception e) {
			System.err.println("Error writing request to output" + e.getMessage());
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
			if (!isInit) {
				throw new InvalidRequestException("User Request is invalid, please try again!");
			}

			if (concept == null) {
				throw new IllegalArgumentException("Compute component is missing!");
			}

			if (values == null || values.isEmpty()) {
				throw new InvalidRequestException("Provided values does not exist!");
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
		if (values == null || values.isEmpty()) {
			throw new InvalidRequestException("Provided values does not exist!");
		}
		ImplementProcessorAPI storage = new ImplementProcessorAPI();

		List<String> responses = new ArrayList<>();
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
