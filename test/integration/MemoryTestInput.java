package integration;

import java.util.ArrayList;
import java.util.List;

import processapi.InputConfig;
import processapi.ReadData;

public class MemoryTestInput implements InputConfig {
private final List<Integer> data;

public MemoryTestInput(List<Integer> data) {
	this.data = new ArrayList<>(data);
}

	@Override
	public List<Integer> getInput() {
		// TODO Auto-generated method stub
		return new ArrayList<>(data);
	}

	@Override
	public ReadData read(String inputSource) {
		// TODO Auto-generated method stub
		return null;
	}

}
