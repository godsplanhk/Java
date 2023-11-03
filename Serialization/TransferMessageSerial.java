import java.io.*;
import java.net.*;
class Message implements Serializable{
    String message,name,ip;
    public Message(String message){
        try
        {this.message = message;name = "hk";ip = InetAddress.getLocalHost().getHostAddress().trim();}
        catch(UnknownHostException e){
            System.out.println(e.getMessage());
        }
}}
public class TransferMessageSerial{
    public static void main(String[] args){
        Message toSend = new Message("Hi this is hk...");
        try{
            FileOutputStream file = new FileOutputStream("message.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(toSend);

    }
    catch(IOException e){
        System.out.println(e.getMessage());
    }
    try {
        FileInputStream file = new FileInputStream("message.ser");
        ObjectInputStream in = new ObjectInputStream(file);
        Message Received = (Message)in.readObject();
        System.out.println("Successfully received message from "+ Received.name +" IP: "+Received.ip);
        System.out.println(Received.name+": "+ Received.message);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    }
}