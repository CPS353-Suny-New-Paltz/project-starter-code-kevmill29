package processapi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ImplementProcessorAPI implements ProcessorAPI {

	@Override
	public List<Integer> input() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// this method reads all the lines on the input file specified by the user
	public List<Integer> read(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		if (!Files.exists(path)) {
			throw new FileNotFoundException("File not found: " + filePath);
		}
		return Files.readAllLines(path).stream()// streams through the strings within the file
				.map(String::trim) // shortens the string if there are empty spaces in the line
				.filter(s -> !s.isEmpty())// checks if line is empty and will skip if the line is not
				.map(Integer::parseInt)// converts String into integer
				.collect(Collectors.toList()); // creates new list and collects all found integers
	}

	@Override
	// this method will write the data from the given equation into a file with the
	// specified location
	public void write(String output, List<Integer> data, String delimiter) throws IOException {
		// this part will join the integers to a single line
		Path parentPath = Paths.get(output);

		String line = data.stream().map(Object::toString).collect(Collectors.joining(delimiter));
		// this part will write the file
		try {
			//check if the parent directory exists if not create the directory
			Files.createDirectories(parentPath.getParent());
			if (!Files.exists(parentPath)) { // check if file already exists 
				Files.write(Paths.get(output), List.of(line));
				System.out.println("File written successfully!");
			} 

		} catch (IOException e) { // if file already exists throw exception
			System.out.println("File already exists!");
			e.printStackTrace();
		}
	}

}
