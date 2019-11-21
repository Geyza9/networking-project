package server;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Server server = null;
        Thread thread = null;

        while(true) {
            switch (scanner.nextLine().toLowerCase()){
                case "start":
                    System.out.println("Starting server");
                    if (server == null){
                        server = new Server();
                        thread = new Thread(server);
                        thread.start();
                    } else {
                        System.out.println("Server already running");
                    }
                    break;
                case "stop":
                    System.out.println("Stopping server");
                    if (server == null){
                        System.out.println("Server not running");
                    } else {
                        server.serverIsRunning = false;
                        thread = null;
                        server = null;
                    }
                    break;
                case "exit":
                    server.serverIsRunning = false;
                    server = null;
                    System.out.println("Program exit");
                    scanner.close();
                    System.exit(0);
                    return;
            }
        }
    }
}
