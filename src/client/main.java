package client;

import java.util.ArrayList;

public class main {
    static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args){




    }




    void sendMessage(String message){
        messages.add(new Message(message));
    }

}
