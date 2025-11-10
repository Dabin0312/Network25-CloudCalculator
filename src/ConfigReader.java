import java.io.File;
import java.util.Scanner;

public class ConfigReader {
    private String host = "localhost";
    private int port = 1234;

    public ConfigReader() {
        try {
            File file = new File("src/server_info.dat");
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                if (sc.hasNext()) host = sc.next();
                if (sc.hasNextInt()) port = sc.nextInt();
                sc.close();
                System.out.println(" Loaded server info from file: " + host + " " + port);
            } else {
                System.out.println("server_info.dat not found. Using default: localhost 1234");
            }
        } catch (Exception e) {
            System.out.println("Error reading server_info.dat, using default values.");
        }
    }

    public String getHost() { return host; }
    public int getPort() { return port; }
}
