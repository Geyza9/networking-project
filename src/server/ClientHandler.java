package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class ClientHandler implements Runnable {
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Socket socket;

    // CLIENT HANDLER - TAKES INPUT AND OUTPUT STREAM
    public ClientHandler(Socket socket,  DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }
    // CLIENT DISCONNECTING
    public void run() { 
        while (true)  
        {
            try
            // GLOBAL CLIENT DISCONNECTED MESSAGE AND REMOVED FROM CLIENT LIST
            {
                Server.instance.globalMessage(dataInputStream.readUTF());
            } catch (IOException e) { 
                Server.instance.globalMessage("Client disconnected");
                Server.instance.clientList.remove(this);
                break;
            } 
        } 
        
        try
        // CLOSE SOCKET AND THE CLIENTS DATA INPUT- AND OUTPUT- STREAM
        {
            this.dataInputStream.close(); 
            this.dataOutputStream.close(); 
            this.socket.close();
        }catch(IOException e){ 
        } 
    } 
} 