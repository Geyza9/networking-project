package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable {
    Scanner scn = new Scanner(System.in);
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    Socket socket;
    boolean isloggedin;

    // constructor
    public ClientHandler(Socket socket,  DataInputStream dataInputStream,
            DataOutputStream dataOutputStream) {
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.socket = socket;
        this.isloggedin = true;
    }
             
          
    public void run() { 
  
        String received; 
        while (true)  
        { 
            try
            { 
                received = dataInputStream.readUTF(); 
                  
                System.out.println(received); 
                  
                if(received.toLowerCase().equals("/logout")){
                    this.isloggedin=false; 
                    this.socket.close(); 
                    break; 
                } 

                Server.instance.globalMessage(received);
                
            } catch (IOException e) { 
                  
                e.printStackTrace(); 
            } 
              
        } 
        try
        { 
            // closing resources 
            this.dataInputStream.close(); 
            this.dataOutputStream.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 
} 