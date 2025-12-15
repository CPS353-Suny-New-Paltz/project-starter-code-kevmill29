package networkapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import assets.UserRequest;
import conceptapi.ComputeComponent;
import conceptapi.ImplementConceptAPI;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;

// 300 error codes come from here
public class MultiThreadedNetworkAPI implements NetworkInterfaceAPI {

    private final ImplementNetworkAPI delegator; 
    private final ExecutorService pool;
    private final ProcessorAPI storage;
    
    // For Networked Storage 
    public MultiThreadedNetworkAPI(ImplementNetworkAPI delegator, ProcessorAPI storage) {
        this.delegator = delegator;
        this.storage = storage; 
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }
    
    //  For Checkpoint test 
    public MultiThreadedNetworkAPI(ImplementNetworkAPI delegator, int nthreads) {
        this.delegator = delegator;
        this.pool = Executors.newFixedThreadPool(nthreads);
        this.storage = new ImplementProcessorAPI(); 
    }
    
   
    public MultiThreadedNetworkAPI(ImplementNetworkAPI delegator) {
        this(delegator, Runtime.getRuntime().availableProcessors());
    }

    @Override
    public List<Integer> respond(String input, String output, char delimiter) {
        ComputeComponent concept = new ImplementConceptAPI();
        UserRequest request = delegator.buildRequest(input, output, delimiter);

        // Validation Checks
        if(input == null || output == null) {
            System.err.println("E300: Request is null! Please try again!");
            return Collections.emptyList();
        }
        
        // 1. READ from Storage (Networked or Local)
        List<Integer> processedData;
        try {
            processedData = storage.read(request.getInputSource());
        } catch(Exception e) {
            System.err.println("E303: Could not read file from storage server!");
            return Collections.emptyList();
        }
        
        // 2. COMPUTE (Multithreaded)
        List<Future<Integer>> futures = new ArrayList<>();
        
        for(Integer value: processedData) {
            futures.add(pool.submit(() -> {
                try {
                    return concept.computeValue(value);
                } catch(Exception e) {
                    System.err.println("E304: Error calculating values!");
                    return 0;
                }
            }));
        }
        
        List<Integer> results = new ArrayList<>();
        for(Future<Integer> f : futures) {
            try {
                results.add(f.get());
            } catch(Exception e) {
                System.err.println("E305: Error adding to futures list!");
                results.add(0);
            }
        }
        
        // 3. WRITE to Storage (Networked or Local)
        try {
            storage.write(output, results, delimiter);
        } catch(Exception e) {
            System.err.println("E306: Could not write file to storage server!");
        }
        
        return results;
    }
    
   

    @Override
    public void shutdown() {
        pool.shutdown();
    }

    // These are required by the interface but not used in this specific flow
    @Override
    public boolean initialize(UserRequest request) { return false; }

    @Override
    public List<String> readRequest(UserRequest request) { return null; }

    @Override
    public int respond(boolean isInit, int valueA, ComputeComponent concept) { return 0; }

    @Override
    public UserRequest buildRequest(String input, String output, char delimiter) { return null; }
    
    @Override
    public void writeRequest(List<Integer> newData, UserRequest request) { }

    // Helper for testing
    public List<String> processRequests(List<String> requests) {
        if (requests == null) return Collections.emptyList();
        List<String> results = new ArrayList<>();
        for (String req : requests) {
            results.add("0"); 
        }
        return results;
    }
}