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
        // SINGLETON SETUP
        if(instance == null) {
            instance = this;
        } else {
            return;
        }
         clientList = new Vector<>();

        try {
            // SERVER SOCKET SETUP USING THE SPECIFIED PORT
            ServerSocket serverSocket = new ServerSocket(port);
            // PRINT SERVER IP IN TERMINAL
            System.out.println("Server running and listening at: " + InetAddress.getLocalHost().getHostAddress() + ":" + port);
            // CONTINUOUSLY LISTEN FOR NEW CONNECTION REQUESTS AND ACCEPT THEM
            // INSTANTIATES A NEW THREAD TO HANDLE THE CLIENTS INCOMING AND OUTGOING MESSAGES
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
            // CLOSE SERVER SOCKET IF THE SERVER IS NO LONGER RUNNING
            System.out.println("Server closed");
            serverSocket.close();
        } catch (IOException e) {
        }
    }

    // METHOD CALLED BY CLIENTHANDLER TO DISTRIBUTE A CLIENTS MESSAGE TO ALL OTHER CLIENTS
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
