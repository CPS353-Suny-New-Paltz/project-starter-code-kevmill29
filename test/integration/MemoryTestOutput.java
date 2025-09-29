package integration;

import java.util.ArrayList;
import java.util.List;

import processapi.OutputConfig;
import processapi.WriteData;

public class MemoryTestOutput implements OutputConfig {
	private final List<String> output = new ArrayList<>();

	@Override
	public WriteData writer(List data) {
		// TODO Auto-generated method stub
		return new WriteData();
	}

	public void write(String value) {
		output.add(value);
	}

	public List<String> getOutput(){
		return output;
	}

}
