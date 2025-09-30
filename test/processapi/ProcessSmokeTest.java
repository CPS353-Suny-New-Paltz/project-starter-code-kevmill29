package processapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import assets.UserInputHandler;


public class ProcessSmokeTest {
    @Test
    public void smokeTest() throws IOException {
        try (MockedConstruction<UserInputHandler> mockedHandler =
                Mockito.mockConstruction(UserInputHandler.class, (mock, context) -> {
                    when(mock.promptValueA()).thenReturn(42);
                })) {

            // ✅ Explicit constructor call for Spoon to detect
            ProcessorAPI realAPI = new ImplementProcessorAPI();

            ProcessorPrototype prototype = new ProcessorPrototype();
            HashMap<Integer, String> result = prototype.processAPI(realAPI);

            assertNotNull(result, "Returned map should not be null");

            UserInputHandler handler = mockedHandler.constructed().get(0);
            verify(handler).promptValueA();
        }
    }
}