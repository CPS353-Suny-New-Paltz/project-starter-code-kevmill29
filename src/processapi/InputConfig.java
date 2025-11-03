package processapi;

import java.util.List;

public interface InputConfig<T>{
List<Integer> getInput();
void read(String inputSource);
}
