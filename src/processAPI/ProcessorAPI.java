package processAPI;
import assets.UserInputHandler;
import project.annotations.ProcessAPI;
@ProcessAPI
public interface ProcessorAPI {
ConvertData read(UserInputHandler handler);	

}
