package client;

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

        //SETUP WINDOW ELEMENTS
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
        // SETUP WINDOW - USER NAME
        userNamePanel.add(userNameLabel, BorderLayout.WEST);
        userNamePanel.add(userNameInput, BorderLayout.EAST);
        // SETUP WINDOW - IP ADDRESS
        ipAdressPanel.add(ipAdressLabel, BorderLayout.WEST);
        ipAdressPanel.add(ipAdressInput, BorderLayout.EAST);
        // SETUP WINDOW - ADDS GUI COMPONENTS TO FRAME
        setupWindow.add(userNamePanel, BorderLayout.NORTH);
        setupWindow.add(ipAdressPanel, BorderLayout.CENTER);
        setupWindow.add(connectButton,BorderLayout.SOUTH);
        setupWindow.setVisible(true);

        //MAIN CHAT ELEMENTS
        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 500);

        // MAIN CHAT - TEXT FIELD BOTTOM PANEL
        JPanel chat = new JPanel();
        JTextField text = new JTextField(25);
        JButton sendButton = new JButton("Send");
        chat.add(text);
        chat.add(sendButton);

        // MAIN CHAT - TEXT AREA + SCROLL PANE
        JTextArea messagesArea = new JTextArea();
        messagesArea.setEditable(false);
        messagesArea.setCaretPosition(messagesArea.getDocument().getLength());
        messagesArea.setText(messages);
        JScrollPane scrollPane = new JScrollPane(messagesArea);

        // MAIN CHAT - ADDS GUI COMPONENTS TO FRAME
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

        // MESSAGE IN THREAD - UPDATES THE MESSAGES STRING AND INSERTS MESSAGE STRING INTO TEXT AREA
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

        // SEND BUTTON ACTION LISTENER TO SEND MESSAGES
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!text.getText().isEmpty()){
                    messageOut.run();
                }
            }
        });

        // ACTION LISTENER ON "ENTER" KEYBOARD INPUT
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!text.getText().isEmpty()){
                    messageOut.run();
                }
            }
        };
        // ADDING THE ACTION LISTENER TO THE TEXT FIELD
        text.addActionListener(action);

        // WINDOW LISTENER TO LOG OUT IF WINDOW IS SHUT DOWN
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loggingOut = true;
                messageOut.run();
            }
        });
        // CHECKS IF INPUT IN THE TWO TEXT FIELDS AND MAKES LOG IN SCREEN INVISIBLE AND CHAT SCREEN VISIBLE
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
