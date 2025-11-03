package process;
import java.io.IOException;
import java.util.List;

import project.annotations.ProcessAPI;
@ProcessAPI
public interface ProcessorAPI {
List<Integer> read(String filePath) throws IOException;
void write(String output, List<Integer> data, String delimiter) throws IOException;
List<Integer> input();

}
