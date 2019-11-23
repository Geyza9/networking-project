package server;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Server server = null;
        Thread thread = null;

        // SERVER LOOKS FOR INPUT INTO THE TERMINAL
        while(true) {
            String input = scanner.nextLine().toLowerCase();
            switch (input){
                // START THE SERVER
                case "start":
                    System.out.println("Starting server");
                    //CHECKS IF YOU ARE ALREADY RUNNING A SERVER
                    if (server == null){
                        server = new Server();
                        thread = new Thread(server);
                        thread.start();
                    } else {
                        System.out.println("Server already running");
                    }
                    break;
                // STOP THE SERVER
                case "stop":
                    System.out.println("Stopping server");
                    // IF THERE IS NO SERVER RUNNING, DO NOTHING
                    if (server == null){
                        System.out.println("Server not running");
                    } else {
                        //IF IT IS RUNNING, STOP THE SERVER
                        server.globalMessage("server: Stopping server");
                        server.serverIsRunning = false;
                        thread = null;
                        server = null;
                    }
                    break;
                // CLOSE THE SERVER AND EXIT PROGRAM
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
                // SEND MESSAGES FROM THE SERVER TO CLIENTS
                default:
                    if (server != null && input.toLowerCase().contains("say:")){
                        server.globalMessage("server: " + input.substring(4));
                    }else{ //IF THE SERVER IS NOT RUNNING, TELL THE USER THE DIFFERENT SERVER COMMANDS
                        System.out.println("the following commands are available; 'start' to start the server, 'stop' to stop the server, 'exit' to exit the application, and 'say:' followed by a message to send the message as the server");
                    }
                break;
            }
        }
    }
}
