package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class ClientHandler implements Runnable {
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Socket socket;

    // CLIENT HANDLER - HANDLES INPUT AND OUTPUT STREAMS
    public ClientHandler(Socket socket,  DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    public void run() {
        // LISTEN FOR CLIENT INPUT STREAM AND OUTPUTS TO ALL CLIENTS
        while (true)  
        {
            try
            {
                Server.instance.globalMessage(dataInputStream.readUTF());
            } catch (IOException e) {
                // IF THIS FAILS, REMOVE THIS CLIENT HANDLER FROM THE SERVER CLIENT LIST
                Server.instance.globalMessage("Client disconnected");
                Server.instance.clientList.remove(this);
                break;
            } 
        } 
        
        try
        // WHEN CLIENT HANDLER IS REMOVED CLOSE DATA STREAMS
        {
            this.dataInputStream.close();
            this.dataOutputStream.close();
            this.socket.close();
        }catch(IOException e){ 
        } 
    } 
} 