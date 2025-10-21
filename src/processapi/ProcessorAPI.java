package processapi;
import java.util.List;

import assets.UserInputHandler;
import assets.UserRequest;
import project.annotations.ProcessAPI;
@ProcessAPI
public interface ProcessorAPI {
ConvertData read(UserRequest input);
WriteData write(UserRequest output);
List<Integer> input();

}
