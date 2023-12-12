package httpserver;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;


public class Server{

    public static int port = 6789;

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

}
