package networkapi;

import networkapi.client.NetworkClient;
import networkapi.server.NetworkServer;
import processapi.server.ProcessAPIServer;

public class AutomatedLauncher {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(">>> STARTING DISTRIBUTED SYSTEM <<<");

        // 1. Start Storage Server
        Thread storageThread = new Thread(() -> {
            try { ProcessAPIServer.main(args); } catch (Exception e) { e.printStackTrace(); }
        });
        storageThread.setDaemon(true); 
        storageThread.start();
        Thread.sleep(1000);

        // 2. Start Network Server
        Thread networkThread = new Thread(() -> {
            try { NetworkServer.main(args); } catch (Exception e) { e.printStackTrace(); }
        });
        networkThread.setDaemon(true);
        networkThread.start();
        Thread.sleep(1000);

        System.out.println("\n>>> SERVERS READY. LAUNCHING CLIENT... <<<\n");

        // 3. HAND CONTROL BACK TO YOUR NETWORK CLIENT
        try {
            NetworkClient.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("\n>>> SYSTEM SHUTDOWN <<<");
    }
}