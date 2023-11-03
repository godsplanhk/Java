import java.util.*;
import java.net.*;
import java.io.*;
public class ChatClient {
    final static int ServerPort = 5000;
    public static void main(String [] args) throws UnknownHostException,IOException{
        Scanner scan = new Scanner(System.in);
        InetAddress ip = InetAddress.getByName("hk");
        Socket s = new Socket(ip,ServerPort);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        Thread receiveMessage = new Thread (new Runnable() {
            public void run(){
                while(true){
                    try{
                        System.out.println(dis.readUTF());
                    }
                    catch(IOException e){
                        System.out.println(e.getMessage());
                        System.out.println("Server Issues.");
                        break;
                    }
                }
            }
        });
        Thread sendMessage = new Thread(new Runnable() {
            public void run(){
                try{
                    while(true){
                    String toSend = scan.nextLine();
                    try {
                        dout.writeUTF(toSend);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Server Issues.");
                        break;
                    }
                    if(toSend.equalsIgnoreCase("logout")){
                        System.out.println("Byeee!! See you soon.....");
                        System.exit(0);
                    }
                }}
                finally{
                    scan.close();
                }
            }
        });
        sendMessage.start();receiveMessage.start();
    }
}
