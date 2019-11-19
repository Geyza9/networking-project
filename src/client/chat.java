package client;

import javax.swing.*;

public class chat {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 450);
        JButton sendButton = new JButton("Send");
        frame.getContentPane().add(sendButton);
        frame.setVisible(true);
    }
}