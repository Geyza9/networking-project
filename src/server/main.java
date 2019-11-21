package server;
import java.util.*;

public class main {

    public static void main(String[] args) {
        System.out.println("Starting server");
        Server server = new Server();
        Thread thread = new Thread(server);
        thread.start();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String command = scanner.nextLine();

            if (command.equals("stop")) {
                System.out.println("Stopping server");
                server.serverIsRunning = false;
                break;
            }
        }
        System.out.println("Stopping program");
    }

    void startServer() {

    }
    void stopServer() {

    }

}
