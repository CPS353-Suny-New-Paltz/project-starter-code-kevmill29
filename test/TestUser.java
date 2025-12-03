

import java.io.File;

import assets.UserRequest;
import networkapi.NetworkInterfaceAPI;


public class TestUser {
	
	// TODO 3: change the type of this variable to the name you're using for your
	// @NetworkAPI interface; also update the parameter passed to the constructor
	private final NetworkInterfaceAPI coordinator;
	public TestUser(NetworkInterfaceAPI coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
		char delimiter = ';';
		String inputPath = "test" + File.separatorChar + "testInputFile.test";
		UserRequest request = new UserRequest(inputPath, outputPath, delimiter);
		// TODO 4: Call the appropriate method(s) on the coordinator to get it to 
		// run the compute job specified by inputPath, outputPath, and delimiter
		coordinator.respond(inputPath, outputPath, delimiter);
	}

}
