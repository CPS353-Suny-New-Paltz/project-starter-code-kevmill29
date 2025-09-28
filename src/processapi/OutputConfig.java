package processapi;

import java.util.List;

public interface OutputConfig<T> {
WriteData writer(List<Integer> data);
}
