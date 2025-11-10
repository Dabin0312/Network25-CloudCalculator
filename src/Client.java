import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        // Load server configuration
        ConfigReader config = new ConfigReader();
        String host = config.getHost();
        int port = config.getPort();

        // Try connecting to the server and communicate through socket streams
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connected to server (" + host + ":" + port + "). Type 'EXIT' to quit.");

            while (true) {
                System.out.print("Enter command: ");
                String msg = sc.nextLine();

                // Exit condition
                if (msg.equalsIgnoreCase("EXIT")) break;

                // Send message to server
                out.println(msg);
                // Receive and print server response
                String response = in.readLine();
                System.out.println("Server says: " + response);
            }

        } catch (IOException e) {
            // Handle connection or I/O errors
            e.printStackTrace();
        }
    }
}



