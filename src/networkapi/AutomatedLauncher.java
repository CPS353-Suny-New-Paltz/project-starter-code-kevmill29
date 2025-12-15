package networkapi;

import networkapi.client.NetworkClient;
import networkapi.server.NetworkServer;
import processapi.server.ProcessAPIServer;

public class AutomatedLauncher {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(">>> STARTING DISTRIBUTED SYSTEM <<<");

        // 1. Start Storage Server (Port 50052) in a separate thread
        Thread storageThread = new Thread(() -> {
            try {
                ProcessAPIServer.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // Daemon means this thread will die automatically when the main program (Client) finishes
        storageThread.setDaemon(true); 
        storageThread.start();

        // Give it a second to initialize
        Thread.sleep(1000);

        // 2. Start Network Server (Port 50051) in a separate thread
        Thread networkThread = new Thread(() -> {
            try {
                NetworkServer.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        networkThread.setDaemon(true);
        networkThread.start();

        // Give it a second to initialize
        Thread.sleep(1000);

        System.out.println("\n>>> SERVERS READY. LAUNCHING CLIENT... <<<\n");

        // 3. Start the Client in the MAIN thread (so you can interact with it)
        try {
            NetworkClient.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("\n>>> SYSTEM SHUTDOWN <<<");
    }
}