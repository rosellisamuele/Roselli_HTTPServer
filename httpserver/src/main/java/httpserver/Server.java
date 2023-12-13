package httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server{

    public static Handler handler;
    public static ServerSocket serverSocket;
    public static int port = 8080;
    public static void main(String[] args) {

        try{
            startServer(port);
            handler = new Handler();

            while(true){
                Socket clientSocket = serverSocket.accept();

                handler.handleRequest(clientSocket);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        
    }

    public static void startServer(int port) throws IOException{
            serverSocket = new ServerSocket(port);
            System.out.println("Server in ascolto sulla porta "+ port);
    }
}
