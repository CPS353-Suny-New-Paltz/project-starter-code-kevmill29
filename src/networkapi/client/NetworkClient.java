package networkapi.client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import networkapi.grpc.ComputeFileRequest;
import networkapi.grpc.ComputeFileResponse;
import networkapi.grpc.NetworkAPIServiceGrpc;

public class NetworkClient {
	private static final int PORT = 50051;
	public static void main(String[] args) {
		
		//first establish connection to the server
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
				.usePlaintext()
				.build();
		
		//next create the blocking stub 
		NetworkAPIServiceGrpc.NetworkAPIServiceBlockingStub stub = NetworkAPIServiceGrpc.newBlockingStub(channel);
		
		Scanner input = new Scanner (System.in);
		
		try {
			System.out.println("----Computation Engine Client----");
			
			//input logic
			String inputPath = "";
			
			System.out.println("Select an option: (1) To input File Path | (2) Input Numbers Directly");
			
			int choice = input.nextInt();
			input.nextLine(); //continues to next line after input
			
			if(choice == 1) {
				System.out.println("Enter input file path: ");
				inputPath = input.nextLine().trim();
			}else {
				System.out.println("Enter numbers separated by comma(e.g. 1, 2, 3): ");
				String rawNumbers = input.nextLine();
				// create a temp file for these numbers so we can send a path
				inputPath = createTempFile(rawNumbers);
			}
				//output path logic
				
				System.out.println("Enter an ouput file path: ");
				String outputPath = input.nextLine().trim();
				
				//delimiter
				System.out.println("Enter delimiter or leave blank and press enter for default: ");
				String delimiter = input.nextLine();
				
				//next build the request
				ComputeFileRequest.Builder requestBuilder = ComputeFileRequest.newBuilder()
						.setInputPath(inputPath)
						.setOutputPath(outputPath);
						
						if(!delimiter.isEmpty()) {
							requestBuilder.setDelimiter(delimiter);
						}
				
						//call the RPC
						
						System.out.println("Sending request to the server...");
						ComputeFileResponse response = stub.processFile(requestBuilder.build());
						
						//response handling
						if(response.hasError()) {
							System.out.println("There was an error! Task failed.");
							System.out.println("Server error: "+response.getError());
						}else {
							System.out.println("Request Completed!");
							System.out.println("Results received: "+ response.getResultsList());
						}
						
			}catch(Exception e) {
				System.err.println("An error has occurred! :"+ e.getMessage());
				
			}finally {
				channel.shutdown();
				input.close();
			}
		}
	
	
	//helper function to create temp file 
	private static String createTempFile(String data) throws IOException {
        File temp = File.createTempFile("compute_input", ".txt");
        FileWriter writer = new FileWriter(temp);
        writer.write(data);
        writer.close();
        return temp.getAbsolutePath();
    }
	
}
