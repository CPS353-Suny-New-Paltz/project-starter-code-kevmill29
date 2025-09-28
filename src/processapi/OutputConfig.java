package processapi;

import java.util.List;

public interface OutputConfig<Integer> {
WriteData writer(List<Integer> data);
}
