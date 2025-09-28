package processapi;

import java.util.List;

import assets.UserInputHandler;

public interface DataStore {
	//WriteData writer(UserInputHandler input);
	WriteData writer(List<Integer> data);
	List<Integer> input();
	
}
