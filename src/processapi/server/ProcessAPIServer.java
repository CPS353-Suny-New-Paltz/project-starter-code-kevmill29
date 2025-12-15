package processapi.server;

import java.io.IOException;
import java.util.List;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import processapi.ImplementProcessorAPI;
import processapi.grpc.ProcessorServiceGrpc;
import processapi.grpc.ReadRequest;
import processapi.grpc.ReadResponse;
import processapi.grpc.WriteRequest;
import processapi.grpc.WriteResponse;

public class ProcessAPIServer {
	
	private static final int PORT = 50052;
	public static void main(String []args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(PORT)
				.addService(new ProcessorServiceImpl())
				.build()
				.start();
		
		System.out.println("Database Server started on port: "+ PORT);
		server.awaitTermination();
	}
	
	static class ProcessorServiceImpl extends ProcessorServiceGrpc.ProcessorServiceImplBase{
		//create an instance for the logic to be held in
		private final ImplementProcessorAPI controller = new ImplementProcessorAPI();
		@Override
		public void readData(ReadRequest request , StreamObserver<ReadResponse> responseObserver) {
			System.out.println("Storage: Received read request for -> " + request.getFilePath());
			ReadResponse.Builder response = ReadResponse.newBuilder();
			
			try {
				//calling method from implementation
				List<Integer> results = controller.read(request.getFilePath());
				
				//pack results into proto
				response.addAllValues(results);
			}catch(Exception e) {
				response.setErrorMessage("Server Read failed: "+ e.getMessage()); 	
			}
			
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
		}
		
		@Override
		public void writeData(WriteRequest request, StreamObserver<WriteResponse>responseObserver) {
			System.out.println("Storage: Received write request for -> " + request.getFilePath());
			WriteResponse.Builder response = WriteResponse.newBuilder();
			
			try {
				char delim = ',';
				//check if delimiter is provided
				if(request.hasDelimiter() && !request.getDelimiter().isEmpty()) {
					delim = request.getDelimiter().charAt(0);
				}
				//calling write method from implementation
				controller.write(request.getFilePath(), request.getValuesList(), delim);
				//if complete set boolean to true
				response.setIsSuccess(true);
			}catch(Exception e) {
				//if incomplete
				response.setIsSuccess(false);
				response.setMessage("Server write failed: "+e.getMessage());
			}
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
		}
		
		
	}

}
