package processapi.client;

import java.util.Collections;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import processapi.ProcessorAPI;
import processapi.grpc.ProcessorServiceGrpc;
import processapi.grpc.ReadRequest;
import processapi.grpc.ReadResponse;
import processapi.grpc.WriteRequest;
import processapi.grpc.WriteResponse;

public class GrpcProcessorAPI implements ProcessorAPI{
private final ManagedChannel channel;
private final ProcessorServiceGrpc.ProcessorServiceBlockingStub stub;

public GrpcProcessorAPI(String host, int port) {
	this.channel = ManagedChannelBuilder.forAddress(host, port)
			.usePlaintext()
			.build();
	this.stub = ProcessorServiceGrpc.newBlockingStub(channel);
}

@Override
public List<Integer> read(String filePath)  {
	try {
		ReadRequest request = ReadRequest.newBuilder()
				.setFilePath(filePath)
				.build();
		
		ReadResponse response = stub.readData(request);
		
		if(response.hasErrorMessage()) {
			System.err.println("Remote error: " + response.getErrorMessage());
			return Collections.emptyList();
		}
		return response.getValuesList();
	}catch(Exception e) {
		System.err.println("gRPC Error: " + e.getMessage());
		return Collections.emptyList();
	}
	
}

@Override
public void write(String output, List<Integer> data, char delimiter) {
	try {
		WriteRequest request = WriteRequest.newBuilder()
				.setFilePath(output)
				.addAllValues(data)
				.setDelimiter(String.valueOf(delimiter))
				.build();
		
		WriteResponse response = stub.writeData(request);
		if(!response.getIsSuccess()) {
			System.err.println("Remote Write Failed :" + response.getMessage());
		}
		
	}catch(Exception e) {
		System.err.println("gRPC Error: "+ e.getMessage());
	}
	
}

@Override
public List<Integer> input() {
	// TODO Auto-generated method stub
	return null;
}

public void shutdown() {
	channel.shutdown();
}
}
