package processapi;

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


	// this method reads all the lines on the input file specified by the user
	@Override
	public List<Integer> read(String filePath) {
	    List<Integer> results = new ArrayList<>();
	    
	    // ... (Keep your existing null checks and file existence checks here) ...
	    
	    try {
	        Path path = Paths.get(filePath);
	        results = Files.readAllLines(path).stream()
	                // Split the line by the delimiter (comma) before parsing
	                .flatMap(line -> java.util.Arrays.stream(line.split(","))) 
	                .map(String::trim)
	                .filter(s -> !s.isEmpty())
	                .map(Integer::parseInt)
	                .collect(Collectors.toList());
	    } catch (IOException e) {
	        System.err.println("File cannot be read!");
	        return Collections.emptyList();
	    } catch (NumberFormatException e) {
	        System.err.println("File contains invalid numbers!");
	        return Collections.emptyList();
	    }
	    
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



	public void write(String output, List<Integer> data, char delimiter) {
	    System.out.println("Storage: Attempting to write " + data.size() + " values to: " + output);
	    
	    if (output == null || output.trim().isEmpty()) {
            System.err.println("Storage Error: Output path cannot be empty.");
            return;
        }
	    
	    java.io.FileWriter writer = null;
	    try {
	        // 1. Create the file writer
	        java.io.File file = new java.io.File(output);
	        
	        // DEBUG: Print the absolute path to confirm where it is actually going
	        System.out.println("Storage: Absolute Path -> " + file.getAbsolutePath());
	        
	        // Ensure parent directories exist
	        if (file.getParentFile() != null) {
	            file.getParentFile().mkdirs(); 
	        }

	        writer = new java.io.FileWriter(file);
	        
	        // 2. Write the data
	        for (int i = 0; i < data.size(); i++) {
	            writer.write(String.valueOf(data.get(i)));
	            if (i < data.size() - 1) {
	                writer.write(delimiter);
	            }
	        }
	        
	        System.out.println("Storage: Write operation successful.");

	    } catch (java.io.IOException e) {
	        System.err.println("Storage Write Error: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        // 3. CRITICAL: Close the file or it will be empty/missing!
	        try {
	            if (writer != null) {
	                writer.flush(); // Force bits to disk
	                writer.close(); // Save and release
	                System.out.println("Storage: File stream closed.");
	            }
	        } catch (java.io.IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
