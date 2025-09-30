package processapi;

import java.util.List;

import integration.TestOutputCollector;
import processapi.ConvertData;
import processapi.ProcessorAPI;
import processapi.WriteData;

public class TestDataStore implements ProcessorAPI {
    private final List<Integer> input;
    private final TestOutputCollector output;

    public TestDataStore(List<Integer> input, TestOutputCollector output) {
        this.input = input;
        this.output = output;
    }


    public List<Integer> input() {
        return input;
    }

   
    public ConvertData read(assets.UserInputHandler handler) {
        return null; // Not used in this test
    }


    public WriteData writer(List<String> data) {
        return null; // Not used in this test
    }
}
