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
        
        //issues with this is that there is only one format that is accepted at the moment for this to read a file
        // the file needs to have lines that only contain one integer if there are more that are listed like 1,2,3,4,5 it will send a number format exception
        //has to be listed like: 
        //1
        //2
        //3
        //4
     


          

            List<Integer> result = testStorage.read(filePath); //returns a null list as method is not implemented yet

            assertEquals(testList, result);

       
			}
        }
    

