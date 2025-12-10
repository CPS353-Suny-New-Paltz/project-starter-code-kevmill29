package networkapi.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import networkapi.ImplementNetworkAPI;

public class NetworkServer {
	// create port number
	private static final int PORT = 50051;
	
	//build the server 
	public static void main(String[] args)throws IOException, InterruptedException{
		Server server = ServerBuilder.forPort(PORT)
				.addService(new ImplementNetworkAPI())
				.build()
				.start();
		
		System.out.println("Server initiated, listening on PORT: "+ PORT);
		server.awaitTermination();
	}
	

	

}
