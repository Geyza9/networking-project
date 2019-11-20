package client;

public class Message {

    String message;
    int id;
    boolean sent;

    //send a message:
    public Message(String msg){
        sent = false;
        message = msg;
    }

    //receive a message:
    public Message(String msg, int id){
        message = msg;

    }

    public String getMessage() {
        return message;
    }
}
