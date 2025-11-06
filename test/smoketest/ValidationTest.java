package smoketest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

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
	assertThrows(FileNotFoundException.class, ()-> mockStorage.read(mockInput), "Because input location string isn't actually pointing to a location should throw an exception"+ " String: "+mockInput);
}

@Test
public void coordinationValidationTest() {
	ProcessorAPI mockStorage = new ImplementProcessorAPI();
	NetworkInterfaceAPI mockAPI = new ImplementNetworkAPI();
	UserRequest request = new UserRequest(null, null, null);
	assertThrows(InvalidRequestException.class, ()-> mockAPI.readRequest(mockStorage, request), "Because request is null should throw an exception" +request);
	
}

}
