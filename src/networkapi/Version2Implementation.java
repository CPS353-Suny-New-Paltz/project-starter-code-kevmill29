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
import io.grpc.stub.StreamObserver;
import networkapi.grpc.ComputeFileRequest;
import networkapi.grpc.ComputeFileResponse;
import networkapi.grpc.NetworkAPIServiceGrpc;
import processapi.ImplementProcessorAPI;
import processapi.ProcessorAPI;

public class Version2Implementation extends NetworkAPIServiceGrpc.NetworkAPIServiceImplBase
implements NetworkInterfaceAPI {

    private final ExecutorService pool;
    private final ProcessorAPI storage;

    public Version2Implementation() {
        // WorkStealingPool is often faster than FixedThreadPool 
        // for tasks that might have varying execution times.
        this.pool = Executors.newWorkStealingPool(); 
        this.storage = new ImplementProcessorAPI();
    }

    @Override
    public List<Integer> respond(String input, String output, char delimiter) {
       
        // We skip the "readRequest -> List<String> -> parse" chain that MultiThreadedNetworkAPI does.
        List<Integer> data;
        try {
            data = storage.read(input);
            if (data == null || data.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            System.err.println("V2 Error: Could not read file.");
            return Collections.emptyList();
        }

        ComputeComponent concept = new ImplementConceptAPI();
 
        List<Future<Integer>> futures = new ArrayList<>(data.size());

        for (Integer value : data) {
            futures.add(pool.submit(() -> {
                try {
                    return concept.computeValue(value);
                } catch (Exception e) {
                    return 0;
                }
            }));
        }

        List<Integer> results = new ArrayList<>(data.size());
        for (Future<Integer> f : futures) {
            try {
                results.add(f.get());
            } catch (Exception e) {
                results.add(0);
            }
        }

        try {
            storage.write(output, results, delimiter);
        } catch (Exception e) {
            System.err.println("V2 Error: Could not write file.");
        }

        return results;
    }

    //  gRPC and Boilerplate Support 
    
    @Override
    public void processFile(ComputeFileRequest request, StreamObserver<ComputeFileResponse> responseObserver) {
        ComputeFileResponse.Builder responseBuilder = ComputeFileResponse.newBuilder();
        try {
            char delim = request.hasDelimiter() && !request.getDelimiter().isEmpty() 
                         ? request.getDelimiter().charAt(0) : ',';
            List<Integer> results = respond(request.getInputPath(), request.getOutputPath(), delim);
            responseBuilder.addAllResults(results);
        } catch (Exception e) {
            responseBuilder.setError("V2 Processing Error: " + e.getMessage());
        }
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
    
    @Override
    public void shutdown() {
    	pool.shutdown(); 
    	}
    @Override
    public boolean initialize(UserRequest request){
    	return true; 
    	}
    @Override
    public List<String> readRequest(UserRequest request){ 
    	return null; 
    	}
    @Override
    
    public int respond(boolean isInit, int valueA, ComputeComponent concept){
    	return 0; 
    	}
    @Override
    public UserRequest buildRequest(String input, String output, char delimiter){ 
    	return null; 
    	}
    @Override
    public void writeRequest(List<Integer> newData, UserRequest request){ 
    	
    }
}