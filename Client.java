import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private static final String SERVER_IP = "localhost"; // Change to server IP if remote
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        System.out.println("=== P2P Chat Client ===");
        System.out.println("Connecting to server at " + SERVER_IP + ":" + SERVER_PORT + "...");

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            System.out.println("[✓] Connected to server!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // Thread to continuously READ messages from server
            Thread readThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("[!] Disconnected from server.");
                }
            });
            readThread.setDaemon(true);
            readThread.start();

            // Main thread handles SENDING messages
            while (scanner.hasNextLine()) {
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("/quit")) {
                    System.out.println("Leaving chat... Goodbye! 👋");
                    break;
                }
                out.println(userInput);
            }

        } catch (ConnectException e) {
            System.out.println("[✗] Could not connect. Is the server running?");
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
