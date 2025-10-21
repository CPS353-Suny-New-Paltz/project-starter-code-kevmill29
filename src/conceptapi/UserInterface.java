package conceptapi;

import java.io.IOException;
import java.util.HashMap;

public interface UserInterface {
HashMap<Integer, String>getValues() throws IOException;

int getValue(String inputSource) throws IOException;
}
