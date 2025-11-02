package integration;

import java.util.ArrayList;
import java.util.List;

import processapi.OutputConfig;

public class MemoryTestOutput implements OutputConfig {
	private final List<String> output = new ArrayList<>();

	@Override
	public void writer(List data) {
		// TODO Auto-generated method stub
		
	}

	public void write(String value) {
		output.add(value);
	}

	public List<String> getOutput(){
		return output;
	}

}
