package server;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server implements Runnable {
    public Vector<ClientHandler> clientList;
    private int port = 6666;
    boolean serverIsRunning = true;
    static Server instance = null;


    @Override
    public void run() {
        if(instance == null) {
            instance = this;
        } else {
            return;
        }
         clientList = new Vector<>();

        try {
            // SERVER RUNNING MESSAGE IN CONSOLE - GIVES THE IP THAT CLIENT NEEDS TO INSERT IN LOGIN SCREEN
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server running and listening at: " + InetAddress.getLocalHost().getHostAddress() + ":" + port);
            // CLIENT CONNECTED TO SOCKET AND ADDED TO CLIENT LIST
            while (serverIsRunning) {
                Socket socket = serverSocket.accept();
                System.out.println("New Client connected");
                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                ClientHandler handler = new ClientHandler(socket, dataIn, dataOut);
                Thread thread = new Thread(handler);
                clientList.add(handler);
                thread.start();
            }
            // SERVER CLOSING SOCKETS
            System.out.println("Server closed");
            serverSocket.close();
        } catch (IOException e) {
        }
    }

    // MESSAGES SENT
    void globalMessage(String message) {
        System.out.println(message);
        for (ClientHandler client : clientList) {
            try {
                client.dataOutputStream.writeUTF(message);
            } catch (IOException e) {
            }
        }
    }
}
