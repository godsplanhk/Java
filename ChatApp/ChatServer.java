import java.io.*;
import java.util.*;
import java.net.*;
public class ChatServer {
    public static HashMap<String,DataOutputStream> clientOS = new HashMap<>();
    public static HashMap<String,DataInputStream> clientIS = new HashMap<>();
    public static void main(String [] args)throws IOException{
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server Started");
        while(true){
            Socket s = null;
                try{
                    System.out.println("Accepting new connection.");
                    s = ss.accept();
                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                    dout.writeUTF("Enter username");
                    String name = dis.readUTF();
                    clientIS.put(name, dis);
                    clientOS.put(name, dout);
                    Thread t = new ClientHandler(s,name,dis,dout);
                    t.start();
                    System.out.println(name+ " joined the server.");
                    System.out.println("Total users = "+clientIS.size());

                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
    }       
    
}
class ClientHandler extends Thread{
    Socket s;String username;DataInputStream dis;DataOutputStream dout;
    ClientHandler(Socket so,String user,DataInputStream dis,DataOutputStream dout)throws IOException{
        username = user;this.dis = dis;this.dout = dout;s = so;
        dout.writeUTF("Welcome to server "+username);
        dout.writeUTF("Available Users are:-\n"+ChatServer.clientOS.keySet());
    }
    Set<String> listUser(){
        return ChatServer.clientOS.keySet();
    }
    public void run(){
        try{while(true){
            String received = dis.readUTF();
            if(received.equalsIgnoreCase("logout")){
                System.out.println(username+ " left the chat.");
                ChatServer.clientIS.remove(username);
                ChatServer.clientOS.remove(username);
                break;
            }
            else if(received.charAt(0)==':'){
                StringTokenizer command = new StringTokenizer(received,":");
                String cmd = command.nextToken();
                switch(cmd){
                    case "lsu":
                        dout.writeUTF("Available users are:-\n"+listUser());
                }
            }
            else if(received.contains("@"))
            {StringTokenizer receivedtoken = new StringTokenizer(received,"@");
            String name = receivedtoken.nextToken();
            String message = receivedtoken.nextToken();
            ChatServer.clientOS.get(name).writeUTF(username+": "+message);
            System.out.println(username+"->"+name);}
        }}
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
