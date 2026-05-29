import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {

    private Socket socket;
    private List<PrintWriter> clientWriters;
    private PrintWriter out;
    private String clientName;

    public ClientHandler(Socket socket, List<PrintWriter> clientWriters) {
        this.socket = socket;
        this.clientWriters = clientWriters;
    }

    @Override
    public void run() {
        try {
            // Setup input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // First message from client is their name
            out.println("Enter your name: ");
            clientName = in.readLine();

            // Add this client's writer to the broadcast list
            clientWriters.add(out);

            System.out.println("[+] " + clientName + " joined the chat.");
            broadcast("[SERVER] " + clientName + " has joined the chat! 🎉");

            // Keep reading messages from this client
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("[" + clientName + "]: " + message);
                broadcast("[" + clientName + "]: " + message);
            }

        } catch (IOException e) {
            System.out.println("[-] " + clientName + " disconnected unexpectedly.");
        } finally {
            // Cleanup on disconnect
            cleanup();
        }
    }

    // Broadcast message to ALL connected clients
    private void broadcast(String message) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }

    // Remove client and close resources
    private void cleanup() {
        if (out != null) {
            clientWriters.remove(out);
        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error closing socket: " + e.getMessage());
        }
        broadcast("[SERVER] " + clientName + " has left the chat. 👋");
        System.out.println("[-] " + clientName + " removed from chat.");
    }
}
