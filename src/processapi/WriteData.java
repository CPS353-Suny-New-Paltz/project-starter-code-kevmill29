package processapi;

import java.util.HashMap;

import assets.UserInputHandler;



public class WriteData {
	public static void writeData(HashMap<Integer, String>map) {
		String path = "Output/answers.txt";
		UserInputHandler handler= new UserInputHandler();
		handler.writeInputToFile(path);
	}

}
