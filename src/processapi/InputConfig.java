package processapi;

import java.util.List;

public interface InputConfig<T>{
List<Integer> getInput();
ReadData read(String inputSource);
}
