package smoketest;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    public void smokeTest() throws IOException {
    	List<Integer>testList = new ArrayList<>();
    	testList.add(1);
    	testList.add(2);
    	testList.add(3);
    	testList.add(4);
    	testList.add(5);
    	testList.add(6);
    	testList.add(7);
    	testList.add(8);
    	testList.add(9);
    	testList.add(10);
    	String filePath = "./test/testFile.txt";
       File testFile = new File(filePath);
        ProcessorAPI testStorage = new ImplementProcessorAPI();
     


          

            List<Integer> result = testStorage.read(filePath); //returns a null list as method is not implemented yet

            assertEquals(testList, result);

       
			}
        }
    

