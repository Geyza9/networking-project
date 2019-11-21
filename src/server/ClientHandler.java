package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class ClientHandler implements Runnable {
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Socket socket;

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
                Server.instance.globalMessage("Client disconnected");
                Server.instance.clientList.remove(this);
                break;
            } 
        } 
        
        try
        { 
            this.dataInputStream.close(); 
            this.dataOutputStream.close(); 
            this.socket.close();
        }catch(IOException e){ 
        } 
    } 
} 