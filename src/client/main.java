package client;

import com.sun.source.doctree.StartElementTree;

import java.awt.event.*;
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
    final static int ServerPort = 6666;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    static Socket socket;
    //END

    //NETWORK STUFF
    static void connect(String ip)throws UnknownHostException, IOException{
        socket = new Socket(ip, ServerPort);

        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }


    //MAIN
    public static void main(String[] args) throws  UnknownHostException, IOException{
        Scanner input = new Scanner(System.in);
        userName = "DudeBro69";
        messages = "Welcome to the chat!";

        //SETUP WINDOW
        JFrame setupWindow = new JFrame("Setup");
        setupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupWindow.setSize(350,150);
        JTextField userNameInput = new JTextField(20);
        JTextField ipAdressInput = new JTextField(20);
        JButton connectButton = new JButton("Connect");
        JLabel userNameLabel = new JLabel("Username:");
        JLabel ipAdressLabel = new JLabel("IP address:");
        JPanel userNamePanel = new JPanel();
        JPanel ipAdressPanel = new JPanel();
        userNamePanel.add(userNameLabel, BorderLayout.WEST);
        userNamePanel.add(userNameInput, BorderLayout.EAST);
        ipAdressPanel.add(ipAdressLabel, BorderLayout.WEST);
        ipAdressPanel.add(ipAdressInput, BorderLayout.EAST);
        setupWindow.add(userNamePanel, BorderLayout.NORTH);
        setupWindow.add(ipAdressPanel, BorderLayout.CENTER);
        setupWindow.add(connectButton,BorderLayout.SOUTH);
        setupWindow.setVisible(true);

        //MAIN CHAT ELEMENTS
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
        JScrollPane scrollPane = new JScrollPane(messagesArea);

        // added the components to the frame
        frame.getContentPane().add(BorderLayout.SOUTH, chat);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);



        //THREADS
        //output thread
        Thread messageOut = new Thread(new Runnable() {
            @Override
            public void run() {
                    if(!loggingOut) {
                        String msg = userName + ": " + text.getText();
                        text.setText("");
                    try{
                        dataOutputStream.writeUTF(msg);
                    } catch (IOException e){
                        e.printStackTrace();
                    }} else {
                        try{
                            socket.close();
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
                        String msg = dataInputStream.readUTF();
                        System.out.println(msg);
                        messages = messages+"\n"+msg;
                        messagesArea.setText(messages);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


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
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!userNameInput.getText().isEmpty()){
                    if(!ipAdressInput.getText().isEmpty()){
                        userName = userNameInput.getText();
                        setupWindow.setVisible(false);
                        frame.setVisible(true);
                        try {
                        connect(ipAdressInput.getText());
                        messageIn.start();
                    } catch (IOException ev){
                            ev.printStackTrace();
                        }}
                }
            }
        });
    }

}
