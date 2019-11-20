package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerTest {
    private static ServerSocket server;
    private static int portNumber = 64209;

    public void main(String args[]) throws IOException, ClassNotFoundException{
        server = new ServerSocket(portNumber);

        while(true) {
            System.out.println("I am Waiting for the Client ;)");

            Socket socket = server.accept();

            ObjectInputStream objInStream = new ObjectInputStream((socket.getInputStream()));
            String message = (String) objInStream.readObject();
            System.out.println("I have recived this message: " + message);

            ObjectOutputStream objOutStream = new ObjectOutputStream(socket.getOutputStream());
            objOutStream.writeObject("Hello Client! :)" + message);

            objInStream.close();
            objOutStream.close();
            socket.close();
            if(message.equalsIgnoreCase("Exit")) break;
        }
        System.out.println("Shutting down Socket Server, Goodb ye :.(");
        server.close();
    }
}
