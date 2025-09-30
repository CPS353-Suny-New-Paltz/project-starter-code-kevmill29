package integration;

import java.util.List;

import processapi.DataStore;
import processapi.InputConfig;
import processapi.OutputConfig;
import processapi.WriteData;

public class ImplementDataStore implements DataStore {
private final InputConfig<Integer> inputConfig;
private final OutputConfig<String> outputConfig;

public ImplementDataStore(InputConfig<Integer> inputConfig, OutputConfig<String> outputConfig) {
	super();
	this.inputConfig = inputConfig;
	this.outputConfig = outputConfig;
}
	
	
	private WriteData writeData() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Integer> input() {
		// TODO Auto-generated method stub
		return inputConfig.getInput();
	}
	public InputConfig<Integer> getInputConfig() {
		return inputConfig;
	}

	public void output(List<String>data){
		outputConfig.writer(data);
	}


	@Override
	public WriteData writer(List<String> data) {
		// TODO Auto-generated method stub
		return new WriteData();
	}


	
}
