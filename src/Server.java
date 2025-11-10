import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) {
        int port = 1234;
        ExecutorService pool = Executors.newFixedThreadPool(10);
        System.out.println("Calculator Server running on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("! Client connected: " + clientSocket.getInetAddress());
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket socket;
    public ClientHandler(Socket socket) { this.socket = socket; }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("[Client " + socket.getInetAddress() + "] Request: " + request);
                String response = processRequest(request);
                out.println(response);
            }
        } catch (IOException e) {
            System.out.println("! Client disconnected: " + socket.getInetAddress());
        }
    }


    private String processRequest(String req) {
        try {
            String[] parts = req.trim().split(" ");
            if (parts.length != 3) return "ERROR ARGU_ERROR";


            String cmd = parts[0].toUpperCase();
            double a = Double.parseDouble(parts[1]);
            double b = Double.parseDouble(parts[2]);

            switch (cmd) {
                case "ADD": return "RESULT " + (a + b);
                case "SUB": return "RESULT " + (a - b);
                case "MUL": return "RESULT " + (a * b);
                case "DIV":
                    if (b == 0) return "ERROR DIV_ZERO";
                    return "RESULT " + (a / b);
                default: return "ERROR CMD_UNKNOWN";
            }
        } catch (Exception e) {
            return "ERROR ARGUMENT_ERROR";
        }
    }
}
