package conceptualAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import assets.userInputHandler;


public class userComponent {
	public static HashMap<Integer, String> getValues(int jobID) throws IOException {
		File file= new File("Output/valueA.txt");
		Scanner input = new Scanner(file);
		
		int valueA = 0;
		while(input.hasNext()) {
			String values = input.next();
			valueA = Integer.parseInt(values);
		}
		userInputHandler handler = new userInputHandler();
		
		return handler.setInputMap(valueA, jobID);
		
		
	}
	
}