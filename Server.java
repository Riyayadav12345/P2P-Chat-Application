import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    // List of all connected client writers (for broadcasting)
    private static List<PrintWriter> clientWriters = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        System.out.println("=== P2P Chat Server Started on Port 1234 ===");

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {
                // Wait for new client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("[+] New client connected: " + clientSocket.getInetAddress());

                // Spawn a new thread for each client
                Thread clientThread = new Thread(new ClientHandler(clientSocket, clientWriters));
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
