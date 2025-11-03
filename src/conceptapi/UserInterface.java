package conceptapi;

import java.io.IOException;
import java.util.Queue;

import assets.UserRequest;

public interface UserInterface {
Queue<Integer>getValues(UserRequest request) throws IOException;
}
