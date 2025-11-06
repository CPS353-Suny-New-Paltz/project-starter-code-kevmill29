package smoketest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import assets.InvalidRequestException;
import assets.InvalidValueException;
import assets.UserRequest;
import conceptapi.ComputeComponent;
import conceptapi.ImplementConceptAPI;
import conceptapi.UserComponent;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;

public class ValidationTest {
@Test
public void computeValidationTest() {
	UserComponent validator = new UserComponent();
	ComputeComponent mockComponent = new ImplementConceptAPI();
	int valueA = -1001;// value above 10000
	assertThrows(InvalidValueException.class, () -> mockComponent.computeValue(valueA), "If value is above 1000 or below -1000 it should throw an exception." 
	+"Validation check on: " +valueA + " Result: " +validator.checkValue(valueA)); //check if computeValues throws the exception
}
@Test
public void storageValidationTest() {
	ProcessorAPI mockStorage = new ImplementProcessorAPI();
	String mockInput = "well..."; //false address should throw an file exception for when the component tries to read
	assertDoesNotThrow(()->{
			List<Integer> results = mockStorage.read(mockInput);
			assertTrue(results.isEmpty(), "should be an empty list since location isn't real");
	},"Since the location is not real and the list is empty an exception should be thrown but should be caught by logic"
	);
}

@Test
public void coordinationValidationTest() {
	ProcessorAPI mockStorage = new ImplementProcessorAPI();
	NetworkInterfaceAPI mockAPI = new ImplementNetworkAPI();
	UserRequest request = new UserRequest(null, null, null);
	assertDoesNotThrow(()->{
		mockAPI.readRequest(mockStorage, request);
		assertTrue(request.getInputSource() == null);
	});
	
}

}
