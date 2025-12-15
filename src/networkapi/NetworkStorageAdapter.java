package networkapi;

import java.util.Collections;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import processapi.ProcessorAPI;
import processapi.grpc.ProcessorServiceGrpc;
import processapi.grpc.ReadRequest;
import processapi.grpc.ReadResponse;
import processapi.grpc.WriteRequest;

public class NetworkStorageAdapter implements ProcessorAPI {

    private final ProcessorServiceGrpc.ProcessorServiceBlockingStub storageStub;

    // Connects to the Storage Server (e.g., running on localhost:50052)
    public NetworkStorageAdapter(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.storageStub = ProcessorServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public List<Integer> read(String filePath) {
        // Convert Java call -> gRPC Request
        ReadRequest req = ReadRequest.newBuilder()
                .setFilePath(filePath)
                .build();

        // Call remote server
        ReadResponse resp = storageStub.readData(req);

        // Convert gRPC Response -> Java List
        return resp.getValuesList();
    }

   
    @Override
    public void write(String output, List<Integer> data, char delimiter) {
        WriteRequest req = WriteRequest.newBuilder()
                .setFilePath(output)
                .addAllValues(data)
                .setDelimiter(String.valueOf(delimiter))
                .build();
        
        // Capture the response!
        processapi.grpc.WriteResponse response = storageStub.writeData(req);
        
        // CHECK if it actually worked
        if (!response.getIsSuccess()) {
            // Throwing this exception makes the Network Server print "E306"
            throw new RuntimeException("Storage Server reported failure: " + response.getMessage());
        }
    }

    @Override
    public List<Integer> input() {
        return Collections.emptyList();
    }
}