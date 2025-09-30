package processapi;

import java.util.List;

import assets.UserInputHandler;
import processapi.ConvertData;
import processapi.InputConfig;
import processapi.OutputConfig;
import processapi.ProcessorAPI;
import processapi.WriteData;

public class ImplementDataStore implements ProcessorAPI {
    public ImplementDataStore(InputConfig<Integer> inputConfig, OutputConfig<String> outputConfig) {
		super();
		this.inputConfig = inputConfig;
		this.outputConfig = outputConfig;
	}

	private final InputConfig<Integer> inputConfig;
    private final OutputConfig<String> outputConfig;

  

	@Override
    public List<Integer> input() {
        return inputConfig.getInput();
    }

   
    public void output(List<String> data) {
        outputConfig.writer(data);
    }

    @Override
    public WriteData writer(List<String> data) {
        return new WriteData(); // You may want to pass `data` to WriteData if needed
    }

    @Override
    public ConvertData read(UserInputHandler handler) {
        return null; // Implement logic as needed
    }

    public InputConfig<Integer> getInputConfig() {
        return inputConfig;
    }


}
