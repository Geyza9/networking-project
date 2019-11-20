package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;


public class main {
    static ArrayList<Message> messages = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();

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
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean connected;

        //try to connect
        //run this if connection is established
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
                }
                for (int i = 0; i < messages.size() ; i++) {
                    System.out.println(messages.get(i).getMessage());
                }
            }
        });


        System.out.println(Integer.toString(users.size()));




    }







}
