package client;

import javax.swing.*;

public class chat {
    public static void main(String args[]) {
        // creating the window for the chat
        JFrame frame = new JFrame("Chat");
        // exit operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // size of the screen
        frame.setSize(300, 450);
        // add button to send the message
        JButton sendButton = new JButton("Send");
        frame.getContentPane().add(sendButton);
        frame.setVisible(true);
    }
}