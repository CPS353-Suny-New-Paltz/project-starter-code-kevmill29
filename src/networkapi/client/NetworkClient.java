package networkapi.client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import assets.VizDataPrep; // Import your Viz Tool
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import networkapi.grpc.ComputeFileRequest;
import networkapi.grpc.ComputeFileResponse;
import networkapi.grpc.NetworkAPIServiceGrpc;

public class NetworkClient {
    private static final int PORT = 50051;

    public static void main(String[] args) {
        
        // 1. Setup Viz Path Dynamically
        String projectRoot = System.getProperty("user.dir");
        String vizFilePath = Paths.get(projectRoot, "data.csv").toString();

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext()
                .build();
        
        NetworkAPIServiceGrpc.NetworkAPIServiceBlockingStub stub = NetworkAPIServiceGrpc.newBlockingStub(channel);
        Scanner input = new Scanner(System.in);
        
        try {
            System.out.println("----Computation Engine Client----");
            System.out.println("Visualization Target: " + vizFilePath);
            
            String inputPath = "";
            
            System.out.println("Select an option: (1) To input File Path | (2) Input Numbers Directly");
            
            int choice = input.nextInt();
            input.nextLine(); 
            
            if(choice == 1) {
                System.out.println("Enter input file path: ");
                inputPath = input.nextLine().trim();
            } else {
                System.out.println("Enter numbers separated by comma (e.g. 1, 2, 3): ");
                String rawNumbers = input.nextLine();
                inputPath = createTempFile(rawNumbers);
            }
                
            System.out.println("Enter an output file path: ");
            String outputPath = input.nextLine().trim();
            
            System.out.println("Enter delimiter or leave blank for default: ");
            String delimiter = input.nextLine();
            
            ComputeFileRequest.Builder requestBuilder = ComputeFileRequest.newBuilder()
                    .setInputPath(inputPath)
                    .setOutputPath(outputPath);
                    
            if(!delimiter.isEmpty()) {
                requestBuilder.setDelimiter(delimiter);
            }
            
            System.out.println("Sending request to the server...");
            ComputeFileResponse response = stub.processFile(requestBuilder.build());
            
            if(response.hasError()) {
                System.out.println("There was an error! Task failed.");
                System.out.println("Server error: " + response.getError());
            } else {
                System.out.println("Request Completed!");
                System.out.println("Results received: " + response.getResultsList());

                // --- NEW: UPDATE VISUALIZATION ---
                VizDataPrep.generateVizFile(inputPath, outputPath, vizFilePath);
            }
                        
        } catch(Exception e) {
            System.err.println("!!! CLIENT ERROR !!!");
            e.printStackTrace(); 
            if (e instanceof io.grpc.StatusRuntimeException) {
                System.err.println("gRPC Status: " + ((io.grpc.StatusRuntimeException) e).getStatus());
            }
            
        } finally {
            channel.shutdown();
            input.close();
        }
    }
    
    private static String createTempFile(String data) throws IOException {
        File temp = File.createTempFile("compute_input", ".txt");
        FileWriter writer = new FileWriter(temp);
        writer.write(data);
        writer.close();
        return temp.getAbsolutePath();
    }
}