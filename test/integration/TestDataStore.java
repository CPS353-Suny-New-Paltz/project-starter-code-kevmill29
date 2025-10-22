package integration;
import java.util.List;
import processapi.ProcessorAPI;
import processapi.TestOutputCollector;
import processapi.WriteData;
import assets.UserInputHandler;  // keep this if ProcessorAPI requires it
import assets.UserRequest;
import processapi.ConvertData;  // adjust if your interface defines this type


public class TestDataStore implements ProcessorAPI{
	
	 private final List<Integer> inputData;
	    private final TestOutputCollector output;

	    public TestDataStore(List<Integer> inputData, TestOutputCollector output) {
	        this.inputData = inputData;
	        this.output = output;
	    }

	    public ConvertData read(UserInputHandler handler) {
	        // Not needed for test-only implementation
	        return null;
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
		public ConvertData read(UserRequest input) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public WriteData write(UserRequest output) {
			// TODO Auto-generated method stub
			return null;
		}
}
