package smoketest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import assets.InvalidRequestException;
import assets.InvalidValueException;
import assets.UserRequest;
import conceptapi.ComputeComponent;
import conceptapi.ImplementConceptAPI;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;

public class ValidationTest {
@Test
public void computeValidationTest() {
	ComputeComponent mockComponent = new ImplementConceptAPI();
	int valueA = 10000;// value above 10000
	assertThrows(InvalidValueException.class, () -> mockComponent.computeValue(valueA)); //check if computeValues throws the exception
}
@Test
public void storageValidationTest() {
	ProcessorAPI mockStorage = new ImplementProcessorAPI();
	String mockInput = "well..."; //false address should throw an file exception for when the component tries to read
	assertThrows(FileNotFoundException.class, ()-> mockStorage.read(mockInput));
}

@Test
public void coordinationValidationTest() {
	ProcessorAPI mockStorage = new ImplementProcessorAPI();
	NetworkInterfaceAPI mockAPI = new ImplementNetworkAPI();
	UserRequest request = new UserRequest(null, null, null);
	assertThrows(InvalidRequestException.class, ()-> mockAPI.readRequest(mockStorage, request));
	
}

}
