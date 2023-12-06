package httpserver;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Server{
    public static ServerSocket serverSocket;
    public static Socket socket;
    public static String hostname;
    public static int port;

    public static void main(String[] args){
        port = setupPort();
        try{
            startServer();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void startServer() throws IOException{
        System.out.println("Server Online");
        serverSocket = new ServerSocket(port);
        for(;;){
            socket = serverSocket.accept();
        }
    }

    public static int setupPort(){
        System.out.println("Inserire la porta su cui hostare il server: ");
        Scanner portScanner = new Scanner(System.in);
        int p = portScanner.nextInt();
        portScanner.close();
        return p;
    }



}
