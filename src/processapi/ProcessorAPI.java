package processapi;
import java.util.List;

import assets.UserInputHandler;
import project.annotations.ProcessAPI;
@ProcessAPI
public interface ProcessorAPI {
ConvertData read(UserInputHandler handler);

List<Integer> input();

}
