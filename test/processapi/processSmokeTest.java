package processapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class processSmokeTest {
@Test
 public void smokeTest() {
	Processapi mockProcess = Mockito.mock(Processapi.class);
	
	// call method
	ProcessapiPrototype mockAPI = new ProcessapiPrototype();
	HashMap<Integer, ArrayList<ArrayList<Integer>>> result = mockAPI.processAPI(mockProcess); 	
	
	//should return a map that is not null
	assertNotNull(result, "should not return a null map");
	
}
}
