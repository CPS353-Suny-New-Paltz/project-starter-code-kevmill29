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
int port = 50051;
        
        System.out.println("DEBUG: Attempting to start Network Server on port " + port + "...");
        
        // This starts the server using the NEW NetworkServerImpl logic
        Server server = ServerBuilder.forPort(port)
                .addService(new NetworkServerImpl())
                .build()
                .start();

        // This is the message you should look for in the console to confirm it's the new code
        System.out.println("Network Compute Server started on port " + port);
        
        server.awaitTermination();
    }
	

	

}
