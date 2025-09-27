package processAPI;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import assets.userInputHandler;
import assets.userRequest;

public class writeData {
	public static void writeData(HashMap<Integer, String>map) {
		String path = "Output/answers.txt";
		userInputHandler handler= new userInputHandler();
		handler.writeInputToFile(path);
	}

}
