package httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server{

    public static void main(String[] args) {

        int port = 2222;

        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server in ascolto sulla porta "+port);

            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata da "+clientSocket.getInetAddress());

                handleClientRequest(clientSocket);

                clientSocket.close();
                System.out.println("Connessione chiusa");
            }

            

        }catch(IOException e){
            e.printStackTrace();
        }

        
    }

    private static void handleClientRequest(Socket clientSocket){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream outputStream = clientSocket.getOutputStream();

            String requestLine = reader.readLine();
            System.out.println("Richiesta ricevuta: "+requestLine);

            String response = "HTTP/1.1 200 OK\r\n\r\nHello, world!";
            outputStream.write(response.getBytes());

            outputStream.flush();

        }catch(IOException e){
            e.printStackTrace();
        }
    }










    /* 
    public static void main(String[] args) throws IOException {     
        HttpServer server = create();
        start(server); 
        System.out.println("Server in esecuzione sulla porta "+port);
    }

    public static HttpServer create() throws IOException{

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new Handler());
        
        return server;
    }

    public static void start(HttpServer server){
        server.setExecutor(null);
        server.start();
    }
*/
}
