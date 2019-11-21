package server;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Server server = new Server();
        Thread thread = new Thread(server);
        thread.start();

        while(true) {
            switch (scanner.nextLine().toLowerCase()){
                case "start":
                    System.out.println("Starting server");
                    server.serverIsRunning = true;
                    break;
                case "stop":
                    System.out.println("Stopping server");
                    server.serverIsRunning = false;
                    break;
                case "exit":
                    System.out.println("Program exit");
                    System.exit(0);
                    return;
            }
        }
    }
}
