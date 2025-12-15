package networkapi.server; // <--- Goes in your subpackage

import java.util.List;

import io.grpc.stub.StreamObserver;
import networkapi.ImplementNetworkAPI;
import networkapi.MultiThreadedNetworkAPI;
import networkapi.NetworkStorageAdapter; // You need this class too (see below)
import networkapi.grpc.ComputeFileRequest;
import networkapi.grpc.ComputeFileResponse;
import networkapi.grpc.NetworkAPIServiceGrpc;
import processapi.ProcessorAPI;

public class NetworkServerImpl extends NetworkAPIServiceGrpc.NetworkAPIServiceImplBase {

	@Override
	public void processFile(ComputeFileRequest request, StreamObserver<ComputeFileResponse> responseObserver) {
	    // TRACE 1: Did the client connect?
	    System.out.println("NetworkServer: 1. Request received from Client.");

	    // 1. Setup the connection to the Storage Server
	    ProcessorAPI networkStorage = new NetworkStorageAdapter("localhost", 50052);
	    ImplementNetworkAPI delegator = new ImplementNetworkAPI(); 

	    // TRACE 2: Are we about to run the engine?
	    System.out.println("NetworkServer: 2. Initializing Engine...");
	    
	    MultiThreadedNetworkAPI engine = new MultiThreadedNetworkAPI(delegator, networkStorage);

	    char delim = request.hasDelimiter() && !request.getDelimiter().isEmpty() 
	                 ? request.getDelimiter().charAt(0) 
	                 : ',';

	    // TRACE 3: Calling the logic
	    System.out.println("NetworkServer: 3. Calling engine.respond()...");
	    
	    List<Integer> results = engine.respond(
	        request.getInputPath(), 
	        request.getOutputPath(), 
	        delim
	    );

	    // TRACE 4: Did we get results?
	    System.out.println("NetworkServer: 4. Engine finished. Results size: " + results.size());

	    ComputeFileResponse response = ComputeFileResponse.newBuilder()
	            .addAllResults(results)
	            .build();

	    responseObserver.onNext(response);
	    responseObserver.onCompleted();
	}
}