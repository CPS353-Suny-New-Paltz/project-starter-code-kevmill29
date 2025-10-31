package smoketest;



import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import assets.UserInputHandler;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;
import processapi.ProcessorPrototype;

public class TestProcessorAPI {

    @Test
    public void smokeTest() {
       
        ProcessorAPI directInstance = new ImplementProcessorAPI();


          

            List<Integer> result = directInstance.input(); //returns a null list as method is not implemented yet

            assertNotNull(result, "Returned list should not be null");

       
			}
        }
    

