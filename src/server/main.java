package server;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Server server = null;
        Thread thread = null;

        while(true) {
            String input = scanner.nextLine().toLowerCase();
            switch (input){
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
                        server.globalMessage("server: Stopping server");
                        server.serverIsRunning = false;
                        thread = null;
                        server = null;
                    }
                    break;
                case "exit":
                    if (server != null){
                        server.globalMessage("server: Stopping server");
                        server.serverIsRunning = false;
                        thread = null;
                        server = null;
                    }
                    System.out.println("Program exit");
                    scanner.close();
                    System.exit(0);
                    return;                    
                default:
                    if (server != null && input.contains("say:")){
                        server.globalMessage("server: " + input.substring(4));
                    }else{
                        System.out.println("the following commands are available; 'start' to start the server, 'stop' to stop the server, 'exit' to exit the application, and 'say:' followed by a message to send the message as the server");
                    }
                break;
            }
        }
    }
}
