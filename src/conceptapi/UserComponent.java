package conceptapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import assets.UserRequest;


public class UserComponent implements UserInterface{
//	public HashMap<Integer, String> getValues() throws IOException {
//		File file= new File("Output/valueA.txt");
//		BufferedReader reader = new BufferedReader(new FileReader(file));
//
//		int valueA = 0;
//		String line;
//		while((line = reader.readLine())!= null) {
//			valueA = Integer.parseInt(line);
//		}
//		UserInputHandler handler = new UserInputHandler();
//
//		return handler.setInputMap();
//
//
//	}
	
	public Queue<Integer> getValues(UserRequest request) throws IOException {
		File file= new File(request.getInputSource());
		BufferedReader reader = new BufferedReader(new FileReader(file));
		Queue<Integer>values = new LinkedList<>();
		int valueA = 0;
		String line;
		while((line = reader.readLine())!= null) {
			valueA = Integer.parseInt(line);
			values.add(valueA);
		}
	

		return values;


	}

}