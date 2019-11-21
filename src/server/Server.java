package server;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server implements Runnable {
    private Vector<ClientHandler> clientList;
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
        int clientCounter = 0;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server running and listening at: " + InetAddress.getLocalHost().getHostAddress() + ":" + port);
            while (serverIsRunning) {
                Socket socket = serverSocket.accept();
                System.out.println("New Client connected");
                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                ClientHandler handler = new ClientHandler(socket, dataIn, dataOut);
                Thread thread = new Thread(handler);
                clientList.add(handler);
                thread.start();
                clientCounter++;
            }
            System.out.println("Server closed");
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void globalMessage(String message) {
        for (ClientHandler client : clientList) {
            try {
                client.dataOutputStream.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
