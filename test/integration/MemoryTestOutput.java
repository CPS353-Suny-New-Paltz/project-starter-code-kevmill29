package integration;

import java.util.ArrayList;
import java.util.List;

import processapi.OutputConfig;
import processapi.WriteData;

public class MemoryTestOutput implements OutputConfig {
	private List<Integer> output = new ArrayList<>();

	@Override
	public WriteData writer(List data) {
		// TODO Auto-generated method stub
		return null;
	}

	public void write(int value) {
		output.add(value);
	}

	public List<Integer> getOutput(){
		return output;
	}

}
