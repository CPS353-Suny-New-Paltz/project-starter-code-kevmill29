package process;

import java.util.List;

public class ImplementDataStore {
private final InputConfig<Integer> inputConfig;
private final OutputConfig<String> outputConfig;

public ImplementDataStore(InputConfig<Integer> inputConfig, OutputConfig<String> outputConfig) {
	super();
	this.inputConfig = inputConfig;
	this.outputConfig = outputConfig;
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


	

	
}
