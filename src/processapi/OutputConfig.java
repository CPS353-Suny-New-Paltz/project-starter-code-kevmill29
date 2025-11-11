package processapi;

import java.util.List;

public interface OutputConfig<T> {
void writer(List<String> data);
}
