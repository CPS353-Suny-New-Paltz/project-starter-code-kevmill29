package integration;

import java.util.List;

import assets.UserInputHandler;
import processapi.ConvertData;
import processapi.InputConfig;
import processapi.OutputConfig;
import processapi.WriteData;

public class ImplementDataStore implements processapi.ProcessorAPI {
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


	public WriteData writer(List<String> data) {
		// TODO Auto-generated method stub
		return new WriteData();
	}

	public ConvertData read(UserInputHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
