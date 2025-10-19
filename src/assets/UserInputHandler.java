package assets;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class UserInputHandler {
<<<<<<< Updated upstream
	private HashMap<Integer, String> inputMap;
	private Scanner input;
=======
	private final HashMap<Integer, String> inputMap;
	
>>>>>>> Stashed changes
	private int valueA;
	private UserRequest request;
	private String delimiter = Character.toString(request.getDelimiter());
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));





	


	public UserInputHandler() {
		
		inputMap = new HashMap<>();
	}

	public HashMap<Integer, String> setInputMap(int valueA, int jobID) {

		int valueB = (int) ((Math.random() * 2001) - 1000) / 2; // creates random second value
		String input = valueA + delimiter + valueB;
		// place inputs into hashmap with a jobID assigned to it
		if(inputMap.containsKey(jobID)) {
			jobID = (int) ((Math.random() * 2000) + 1); //randomizes jobID again if same jobID is created
		}
		inputMap.put(jobID, input);

		return inputMap;
	}


	public int promptValueA() throws IOException {

		System.out.println("Enter value for a: \n");
		String value = reader.readLine()
;		valueA = Integer.parseInt(value);
		// For euler project 27 user cannot enter an integer value higher than 1000 or
		// lower than -1000
		if (valueA > 1000 || valueA < -1000 || valueA == 0) {
			System.out.println("Invalid input please try again!");
			System.out.println("Enter value for a: \n");
			value = reader.readLine();		
			valueA = Integer.parseInt(value);
		}

		writeValueA(valueA, "Output/valueA.txt");//creates file that just stores valueA to be used later

		return valueA;

	}

	public int getValueA(HashMap<Integer, String> map, int jobID) {

		String values = map.get(jobID);
		String[]splitter = values.split(delimiter);
		valueA = Integer.parseInt(splitter[0]);
		return valueA;

	}

	public int getValueB(HashMap<Integer, String> map, int jobID) {

		String values = map.get(jobID);
		String[]splitter = values.split(delimiter);
		int valueB = Integer.parseInt(splitter[1]);
		return valueB;
	}


public void writeInputToFile(String filePath) {
    try (FileWriter writer = new FileWriter(filePath)) {
        for (Integer jobID : inputMap.keySet()) {
            String inputLine = jobID + ": " + inputMap.get(jobID) + "\n";
            writer.write(inputLine);
        }
        System.out.println("Input data successfully written to " + filePath);
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }

}

public void writeValueA(int valueA,String filePath) throws IOException {
	try(FileWriter writer = new FileWriter(filePath)){
		writer.write(valueA);
		writer.close();
	}

}


}
