package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;


public class main {
    static ArrayList<Message> messages = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();

    //NETWORK STUFF
    final static int ServerPort = 64209;
    static byte[] ipa = new byte[4];
    //END

    //FUNCTIONS
    private static void sendMessage(String message){
        messages.add(new Message(message));
        //send the message to the server
    }

    private static void receiveMessage(String message, int id){
        messages.add(new Message(message,id));
    }

    private static void requestUserList(){
        //fill users arraylist with users from server
        for (int i = 0; i < 2 ; i++) {
            users.add(new User(i,false));
        }
        users.add(new User(2,true));
    }


    //MAIN
    public static void main(String[] args) throws  UnknownHostException, IOException{
        Scanner input = new Scanner(System.in);

        //NETWORK STUFF
        //ipa[0] = (byte)172; ipa[1] = (byte)24; ipa[2] = (byte)214; ipa[3] = (byte)168;
        //InetAddress ip = InetAddress.getByAddress(ipa);
        Socket socket = new Socket("192.168.137.1", 6666);

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        //THREADS
        //output thread
        Thread messageOut = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    String msg = input.nextLine();

                    try{
                        outputStream.writeUTF(msg);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread messageIn = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        String msg = inputStream.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        messageIn.start();
        messageOut.start();

        /*boolean connected;
        connected = true;
        //add users to list based on server
        if(connected){
            requestUserList();
        }

        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500);

        // bottom panel with a text field to chat in
        JPanel chat = new JPanel();
        JTextField text = new JTextField(10); // accepts up to 10 characters
        JButton sendButton = new JButton("Send");
        chat.add(text);
        chat.add(sendButton);

        // where the messages received will be shown
        JLabel clientChat = new JLabel("");

        // added the components to the frame
        frame.getContentPane().add(BorderLayout.SOUTH, chat);
        frame.getContentPane().add(BorderLayout.NORTH, clientChat);
        frame.setVisible(true);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!text.getText().isEmpty()){
                    messages.add(new Message(text.getText(),0));
                    text.setText("");
                }
                for (int i = 0; i < messages.size() ; i++) {
                    System.out.println(messages.get(i).getMessage());
                }
            }
        });

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!text.getText().isEmpty()){
                    messages.add(new Message(text.getText(),0));
                    text.setText("");
                }
                for (int i = 0; i < messages.size() ; i++) {
                    System.out.println(messages.get(i).getMessage());
                }
            }
        };

        text.addActionListener(action);

        System.out.println(Integer.toString(users.size()));
*/


    }







}
