package processapi;

import java.util.List;

public interface DataStore {
	//WriteData writer(UserInputHandler input);
	WriteData writer(List<String> data);
	List<Integer> input();

}
