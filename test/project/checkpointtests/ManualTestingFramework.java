package project.checkpointtests;

import java.io.IOException;
import java.util.List;

import assets.UserRequest;
import conceptapi.ComputeComponent;
import conceptapi.ImplementConceptAPI;
import networkapi.ImplementNetworkAPI;
import networkapi.NetworkInterfaceAPI;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;

public class ManualTestingFramework {

    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        // TODO 1:
        // Instantiate a real (ie, class definition lives in the src/ folder) implementation
        // of all 3 APIs
        NetworkInterfaceAPI netAPI = new ImplementNetworkAPI();
        ProcessorAPI processor = new ImplementProcessorAPI();
        ComputeComponent computer = new ImplementConceptAPI();
        //
        // TODO 2:
        // Run a computation with an input file of <root project dir>/manualTestInput.txt
        UserRequest request = new UserRequest(INPUT,OUTPUT, ",");
        List<Integer> newData;
        //next write new data to a file in designated location
        try {
        	newData =  netAPI.respond(netAPI.initialize(request), computer, netAPI.readRequest(processor, request)); // create a list of integers from the data inside the request
            netAPI.writeRequest(processor, newData, request);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // and an output of <root project dir>/manualTestOutput.txt, with a delimiter of ',' 
        //
        //
        // Helpful hint: the working directory of this program is <root project dir>,
        // so you can refer to the files just using the INPUT/OUTPUT constants. You do not 
        // need to (and should not) actually create those files in your repo
    }
}
