package conceptapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

import assets.UserInputHandler;


public class UserComponent implements UserInterface{
	public HashMap<Integer, String> getValues() throws IOException {
		File file= new File("Output/valueA.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));

		int valueA = 0;
		String line;
		while((line = reader.readLine())!= null) {
			valueA = Integer.parseInt(line);
		}
		UserInputHandler handler = new UserInputHandler();

		return handler.setInputMap();


	}
	public int getValue(String inputSource) throws NumberFormatException, IOException {
		File file= new File(inputSource);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		int valueA = 0;
		String line;
		while((line = reader.readLine())!= null) {
			valueA = Integer.parseInt(line);
		}
		UserInputHandler handler = new UserInputHandler();

		return valueA;

	}

}