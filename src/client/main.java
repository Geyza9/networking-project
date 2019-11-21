package client;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;


public class main {
    static boolean loggingOut;
    static String userName;
    static String messages;

    //NETWORK STUFF
    final static int ServerPort = 64209;
    static byte[] ipa = new byte[4];
    //END


    //MAIN
    public static void main(String[] args) throws  UnknownHostException, IOException{
        Scanner input = new Scanner(System.in);
        userName = "DudeBro69";
        messages = "Welcome to the chat!";

        //GUI STUFF
        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500);

        // bottom panel with a text field to chat in
        JPanel chat = new JPanel();
        JTextField text = new JTextField(25);
        JButton sendButton = new JButton("Send");
        chat.add(text);
        chat.add(sendButton);

        // where the messages received will be shown
        JTextArea messagesArea = new JTextArea();
        messagesArea.setEditable(false);
        messagesArea.setCaretPosition(messagesArea.getDocument().getLength());
        messagesArea.setText(messages);

        //scrollpane
        JScrollPane scrollPane = new JScrollPane(messagesArea);

        // added the components to the frame
        frame.getContentPane().add(BorderLayout.SOUTH, chat);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.setVisible(true);

        //NETWORK STUFF
        ipa[0] = (byte)192; ipa[1] = (byte)168; ipa[2] = (byte)43; ipa[3] = (byte)186; //INPUT IP4 ADDRESS HERE
        InetAddress ip = InetAddress.getByAddress(ipa);
        //InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip, 6666);

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        //THREADS
        //output thread
        Thread messageOut = new Thread(new Runnable() {
            @Override
            public void run() {
                    if(!loggingOut) {
                        String msg = userName + ": " + text.getText();
                        text.setText("");
                    try{
                        outputStream.writeUTF(msg);
                    } catch (IOException e){
                        e.printStackTrace();
                    }} else {
                        try{
                            outputStream.writeUTF("/logout");
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
                        messages = messages+"\n"+msg;
                        messagesArea.setText(messages);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        messageIn.start();


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!text.getText().isEmpty()){
                    messageOut.run();
                }
            }
        });

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!text.getText().isEmpty()){
                    messageOut.run();
                }
            }
        };
        text.addActionListener(action);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loggingOut = true;
                messageOut.run();
            }
        });
    }

}
