import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        // 서버 정보 불러오기
        ConfigReader config = new ConfigReader();
        String host = config.getHost();
        int port = config.getPort();

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connected to server (" + host + ":" + port + "). Type 'EXIT' to quit.");

            while (true) {
                System.out.print("Enter command: ");
                String msg = sc.nextLine();
                if (msg.equalsIgnoreCase("EXIT")) break;

                out.println(msg);
                String response = in.readLine();
                System.out.println("Server says: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



