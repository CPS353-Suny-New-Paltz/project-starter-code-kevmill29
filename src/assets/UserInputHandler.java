package assets;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UserInputHandler {
	private final HashMap<Integer, String> inputMap;
	private int valueA;
	private String delimiter;
	private UserRequest request;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private int jobID;
	private int lastJobID;





	public String getDelimiter() {
		return request.getDelimiter();
	}

	


	public UserInputHandler() {
		inputMap = new HashMap<>();
		this.request = request;
	}

	public HashMap<Integer, String> setInputMap() {
		int jobID= getJobID();;
		int valueA=0;
		try {
			valueA = promptValueA();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int valueB = (int) ((Math.random() * 2001) - 1000) / 2; // creates random second value
		String input = valueA + delimiter + valueB;
		// place inputs into hashmap with a jobID assigned to it
		if(inputMap.containsKey(jobID)) {
			jobID = getJobID();; //randomizes jobID again if same jobID is created
		}
		lastJobID = jobID;
		inputMap.put(jobID, input);

		return inputMap;
	}
	//overloaded method for if the user does not enter a input location
	public HashMap<Integer, String> setInputMap(String valueA) {
		int jobID = getJobID();
		
		int valueB = (int) ((Math.random() * 2001) - 1000) / 2; // creates random second value
		String input = valueA + delimiter + valueB;
		// place inputs into hashmap with a jobID assigned to it
		if(inputMap.containsKey(jobID)) {
			jobID = getJobID()); //randomizes jobID again if same jobID is created
		}
		inputMap.put(jobID, input);
		lastJobID = jobID;

		return inputMap;
	}

	public int getJobID() {
		return (int)(Math.random()*2000);
	}

	public int promptValueA() throws IOException {
	
		 //gets delimiter from userRequest
		System.out.println("Enter value for a: \n");
		String value = reader.readLine();
		valueA = Integer.parseInt(value);
		// For euler project 27 user cannot enter an integer value higher than 1000 or
		// lower than -1000
		while (valueA > 1000 || valueA < -1000 || valueA == 0) {
			System.out.println("Invalid input please try again!");
			System.out.println("Enter value for a: \n");
		     value = reader.readLine();
			valueA = Integer.parseInt(value);
		}

		writeValueA(valueA, "Output/valueA.txt");//creates file that just stores valueA to be used later

		return valueA;

	}

	public int getValueA(HashMap<Integer, String> map) {

		String values = map.get(lastJobID);
		String[]splitter = values.split(delimiter);
		valueA = Integer.parseInt(splitter[0]);
		return valueA;

	}

	public int getValueB(HashMap<Integer, String> map) {

		String values = map.get(lastJobID);
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
