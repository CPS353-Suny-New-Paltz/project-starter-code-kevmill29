package processapi;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import assets.UserInputHandler;
import assets.UserRequest;



public class WriteData {
	public static void writeData(List<Integer> newList) {
		String path = "Output/answers.txt";
		UserInputHandler handler= new UserInputHandler();
		handler.writeInputToFile(path);
	}
	

//	public static void writeData(UserRequest user) {
//		String outputPath = user.getOutputDestination();
//		
//		UserInputHandler handler = new UserInputHandler();
//		handler.writeInputToFile(outputPath);
//	}
	
	//This method takes in the user request and gets the output destination and write a file to that output destination
	public static File writeData(String output) {
		UserInputHandler handler = new UserInputHandler();
		return handler.writeInputToFile(output);
	}

}
