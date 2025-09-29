package processapi;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import assets.UserInputHandler;

public class ProcessSmokeTest {
	@Test
	public void smokeTest() throws IOException {
		try(MockedConstruction<UserInputHandler> mockedHandler =
				Mockito.mockConstruction(UserInputHandler.class, (mock, context)->{
					when(mock.promptValueA()).thenReturn(42);
				})){


			 ProcessorAPI mockAPI = mock(ProcessorAPI.class);
		        ProcessorPrototype prototype = new ProcessorPrototype();

		        // Call the method under test
		        HashMap<Integer, String> result = prototype.processAPI(mockAPI);

		        // Assert that the result is not null (or whatever behavior you expect)
		        Assertions.assertNotNull(result, "Returned map should not be null");

		        // Optional: check if the mocked method was called
		        UserInputHandler handler = mockedHandler.constructed().get(0);
		        verify(handler).promptValueA();
		}
	}
}
