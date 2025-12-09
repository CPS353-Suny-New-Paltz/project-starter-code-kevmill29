package networkapi.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import networkapi.grpc.ComputeFileRequest;
import networkapi.grpc.ComputeFileResponse;
import networkapi.grpc.NetworkAPIServiceGrpc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//THIS IS MEANT TO TEST NETWORKSERVER
//RUN NETWORK SERVER FIRST AND MAKE SURE ITS RUNNING

public class TestClient {

    private static final int PORT = 50051;

    public static void main(String[] args) {

        // 1. Establish connection
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", PORT)
                .usePlaintext()
                .build();

        // 2. Create Stub
        NetworkAPIServiceGrpc.NetworkAPIServiceBlockingStub stub =
                NetworkAPIServiceGrpc.newBlockingStub(channel);

        try {
            System.out.println("--- Starting Automated Test Suite ---");

            // --- TEST 1: Happy Path (Space Delimiter / Default) ---
            System.out.println("\n1. Test: Valid File (Space Delimited)");
            String file1 = createTempFile("10 20 30 40 50");
            
            ComputeFileRequest req1 = ComputeFileRequest.newBuilder()
                    .setInputPath(file1)
                    .setOutputPath("output_1.txt")
                    .build();

            ComputeFileResponse resp1 = stub.processFile(req1);
            
            if (resp1.hasError()) {
                System.out.println("FAILED: " + resp1.getError());
            } else {
                System.out.println("SUCCESS: " + resp1.getResultsList());
            }


            // --- TEST 2: Custom Delimiter (Commas) ---
            System.out.println("\n2. Test: Valid File (Comma Delimited)");
            String file2 = createTempFile("100,200,300,400");
            
            ComputeFileRequest req2 = ComputeFileRequest.newBuilder()
                    .setInputPath(file2)
                    .setOutputPath("output_2.txt")
                    .setDelimiter(",") // <--- Specifying delimiter
                    .build();

            ComputeFileResponse resp2 = stub.processFile(req2);
            
            if (resp2.hasError()) {
                System.out.println("FAILED: " + resp2.getError());
            } else {
                System.out.println("SUCCESS: " + resp2.getResultsList());
            }


            // --- TEST 3: Error Case (File Not Found) ---
            System.out.println("\n3. Test: File Not Found");
            
            ComputeFileRequest req3 = ComputeFileRequest.newBuilder()
                    .setInputPath("C:/bad/path/fake_ghost_file.txt")
                    .setOutputPath("output_3.txt")
                    .build();

            ComputeFileResponse resp3 = stub.processFile(req3);
            
            if (resp3.hasError()) {
                System.out.println("CORRECTLY CAUGHT ERROR: " + resp3.getError());
            } else {
                System.out.println("FAILED: Expected an error but got results: " + resp3.getResultsList());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.shutdown();
        }
    }

    // Helper to create real files on the fly so the test actually works
    private static String createTempFile(String content) throws IOException {
        File temp = File.createTempFile("test_data", ".txt");
        FileWriter writer = new FileWriter(temp);
        writer.write(content);
        writer.close();
        temp.deleteOnExit(); // Auto-delete after run (optional)
        return temp.getAbsolutePath();
    }
}