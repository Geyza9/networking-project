package server;

import java.io.*;
import java.util.*;
import java.net.*;

//Server
public class Server {

    //Storage for Clients
    static Vector<ClientHandler> clientList = new Vector<>();


    static int clientCounter = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(64209);

        Socket socket;
        //Look for Clients
        while (true)
        {
            socket = serverSocket.accept();

            System.out.println("A new client is trying to connect! " + socket);

            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            System.out.println("Handeling Client...");

            ClientHandler handler = new ClientHandler(socket, "Client " + clientCounter, dataIn, dataOut);

            Thread thread = new Thread(handler);

            System.out.println("Adding Client to Client list");

            clientList.add(handler);

            thread.start();

            clientCounter++;
        }
    }
}
