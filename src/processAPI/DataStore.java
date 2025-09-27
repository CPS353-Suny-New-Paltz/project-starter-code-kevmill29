package processAPI;

import assets.UserInputHandler;

public interface DataStore {
	WriteData writer(UserInputHandler input);
}
