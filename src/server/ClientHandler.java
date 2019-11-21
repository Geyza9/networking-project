package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable 
{ 
    Scanner scn = new Scanner(System.in); 
    private String name; 
    final DataInputStream dataInputStream; 
    final DataOutputStream dataOutputStream; 
    Socket socket; 
    boolean isloggedin; 
      
    // constructor 
    public ClientHandler(Socket socket, String name, DataInputStream dataInputStream, DataOutputStream dataOutputStream) { 
        this.dataInputStream = dataInputStream; 
        this.dataOutputStream = dataOutputStream; 
        this.name = name; 
        this.socket = socket; 
        this.isloggedin=true; 
    } 
  
    @Override
    public void run() { 
  
        String received; 
        while (true)  
        { 
            try
            { 
                received = dataInputStream.readUTF(); 
                  
                System.out.println(received); 
                  
                if(received.equals("logout")){ 
                    this.isloggedin=false; 
                    this.socket.close(); 
                    break; 
                } 
                  
                StringTokenizer st = new StringTokenizer(received, "#"); 
                String MsgToSend = st.nextToken(); 
                String recipient = st.nextToken(); 
  
                for (ClientHandler mc : Server.clientList)
                { 
                    if (mc.name.equals(recipient) && mc.isloggedin==true)  
                    { 
                        mc.dataOutputStream.writeUTF(this.name+" : "+MsgToSend); 
                        break; 
                    } 
                } 
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