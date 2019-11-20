package client;

import javax.swing.*;
import java.awt.*;

public class chat {
    public static void main(String args[]) {
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
    }
}