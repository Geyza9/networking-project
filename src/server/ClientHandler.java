package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Socket socket;

    // constructor
    public ClientHandler(Socket socket,  DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }
          
    public void run() { 
        while (true)  
        { 
            try
            { 
                Server.instance.globalMessage(dataInputStream.readUTF());
            } catch (IOException e) { 
                e.printStackTrace();
                break;
            } 
        } 
        Server.instance.globalMessage("Client disconnected");
        try
        { 
            this.dataInputStream.close(); 
            this.dataOutputStream.close(); 
            this.socket.close();
        }catch(IOException e){ 
            e.printStackTrace();
        } 
    } 
} 