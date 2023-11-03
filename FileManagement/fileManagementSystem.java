import java.util.*;
import java.io.*;
public class fileManagementSystem {
    private static void readFile (String filePath)throws IOException{
            File file = new File(filePath);
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
            System.out.println(scan.nextLine());
        }
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the file path which you want to read?");
        String filePath = scan.nextLine();
        try {
            readFile(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally{
            scan.close();
        }
    }
}
