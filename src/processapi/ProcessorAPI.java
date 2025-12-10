package processapi;
import java.util.List;

import project.annotations.ProcessAPI;
@ProcessAPI
public interface ProcessorAPI {
List<Integer> read(String filePath);
void write(String output, List<Integer> data, char delimiter);
List<Integer> input();

}
