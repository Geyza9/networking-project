package client;

import java.util.ArrayList;
import java.util.Scanner;

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


        System.out.println(Integer.toString(users.size()));




    }







}
