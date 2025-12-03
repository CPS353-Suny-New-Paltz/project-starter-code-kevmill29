package processapi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import conceptapi.ComputeComponent;
import conceptapi.ImplementConceptAPI;

public class ImplementProcessorAPI implements ProcessorAPI {

	@Override
	public List<Integer> input() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// this method reads all the lines on the input file specified by the user
	public List<Integer> read(String filePath) {
		List<Integer> results = new ArrayList<>();
		if(filePath == null || filePath.isEmpty()) {
			System.err.println("File path cannot be empty!");
			return Collections.emptyList();
		}
		Path path = Paths.get(filePath);
		//validation check on filepath
		try {
		if (!Files.exists(path)) {
			System.err.println("File not found: " + filePath);
			return Collections.emptyList();
		}
		results= Files.readAllLines(path).stream()// streams through the strings within the file
				.map(String::trim) // shortens the string if there are empty spaces in the line
				.filter(s -> !s.isEmpty())// checks if line is empty and will skip if the line is not
				.map(Integer::parseInt)// converts String into integer
				.collect(Collectors.toList()); // creates new list and collects all found integers
		}catch(IOException e) {
			System.err.println("File cannot be read!");
			return Collections.emptyList();
		}
		// do not need to add catch for FileNotFoundException already handled by IOException
		
		return results;
	}
	
	//overloading method for testing harness
	public List<String> read(List<String> values, String output) {
		ComputeComponent computer = new ImplementConceptAPI();
		List<String> results = new ArrayList<>();
		if(values == null || values.isEmpty()) {
			System.err.println("Cannot parse empty list!");
			Collections.emptyList();
		}
		if(output == null || output.isEmpty()) {
			System.err.println("Output is null!");
			Collections.emptyList();
		}
		
	try {
		results= values.stream()// streams through the strings within the file
				.map(String::trim) // shortens the string if there are empty spaces in the line
				.filter(s -> !s.isEmpty())// checks if line is empty and will skip if the line is not
				.map(Integer::parseInt)// converts String into integer
				.map(computer::computeValue)
				.map(String::valueOf)
				.collect(Collectors.toList()); // creates new list and collects all found integers
		}catch(Exception e) {
			System.err.println("File cannot be read!");
			return Collections.emptyList();
		}

		
		return results;
	}



	@Override
	// this method will write the data from the given equation into a file with the
	// specified location
	public void write(String output, List<Integer> data, char delimiter)  {
		if(output == null || output.isEmpty()) {
			System.err.println("File path cannot be empty!");
			return;
		}
	
			//create an empty file instead of throwing a null pointer exception
			//output does not need validation as it will be creating a new directory if does not exist
			//delimiter will use default "," if not assigned
			try {
			Files.write(Paths.get(output), List.of());
			}catch(IOException e) {
				e.printStackTrace();
			}
		
		//this part will join the integers to a single line
		String line = data.stream()
				.map(Object::toString)
				.collect(Collectors.joining(String.valueOf(delimiter)));
		//this part will write the file
		try {
			
			if(data == null || data.isEmpty()) {
				System.err.println("Data is empty or null!");
				return;
			}
			
		Files.write(Paths.get(output), List.of(line));
		}catch(IOException e) {
			System.err.println("Error writing file to output");
			
		}catch(IllegalArgumentException e) {
			System.err.println("Computation error: " +e.getMessage());
		}
	}

}
