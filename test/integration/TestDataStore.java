package integration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import processapi.ProcessorAPI;
import processapi.TestOutputCollector;


public class TestDataStore implements ProcessorAPI{
	
	 private final List<Integer> inputData;
	    private final TestOutputCollector output;

	    public TestDataStore(List<Integer> inputData, TestOutputCollector output) {
	        this.inputData = inputData;
	        this.output = output;
	    }

	  

	    public List<Integer> input() {
	        // Return the test input directly
	        return inputData;
	    }

	    // Optional helper if you want to forward writes to output collector
	    public void write(String value) {
	        output.write(value);
	    }


		@Override
		public List<Integer> read(String filePath) throws IOException {
			
			return Files.readAllLines(Paths.get(filePath)).stream()//streams through the strings within the file
					.map(String::trim) // shortens the string if there are empty spaces in the line
					.filter(s->!s.isEmpty())// checks if line is empty and will skip if the line is not
					.map(Integer::parseInt)// converts String into integer
					.collect(Collectors.toList()); // creates new list and collects all found integers
		}

	

		@Override
		public void write(String output, List<Integer> data, String delimiter) throws IOException {
			// TODO Auto-generated method stub
			
		}
}
