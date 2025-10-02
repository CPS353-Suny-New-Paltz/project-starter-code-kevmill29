package smoketest;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import assets.UserInputHandler;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorPrototype;
import processapi.ProcessorAPI;

public class TestProcessorAPI {

    @Test
    public void smokeTest() {
       
        ProcessorAPI directInstance = new ImplementProcessorAPI();

        // Use Mockito to mock UserInputHandler
        try (MockedConstruction<UserInputHandler> mockedHandler =
                Mockito.mockConstruction(UserInputHandler.class, (mock, context) -> {
                    when(mock.promptValueA()).thenReturn(42);
                })) {

          
            ProcessorPrototype prototype = new ProcessorPrototype();

            HashMap<Integer, String> result = prototype.processAPI(directInstance);

            assertNotNull(result, "Returned map should not be null");

       
			}
        }
    }

