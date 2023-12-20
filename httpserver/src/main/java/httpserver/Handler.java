package httpserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;

public class Handler {

    public BufferedReader reader;
    public DataOutputStream outputStream;

    public void handleRequest(Socket clientSocket) throws IOException {

        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outputStream = new DataOutputStream(clientSocket.getOutputStream());

        String request = reader.readLine();
        System.out.println(request);

        if (request != null && request.startsWith("GET")) {
            try {


                String uri = request.split(" ")[1];
                System.out.println(uri);

                switch (uri) {
                    case "/":
                        respond("text/html", "website/index.html");
                        break;

                    case "/?":
                        respond("text/html", "website/index.html");
                        break;

                    case "/style.css":
                        respond("text/css", "website/css/style.css");
                        break;

                    case "/script.js":
                        respond("application/javascript", "website/js/script.js");
                        break;

                    case "/website/images/winner.gif":
                        respond("images/gif", "website/images/winner.gif");
                        break;

                    case "/error":
                        respond("text/html", "website/pages/error.html");
                        break;

                    case "/data.json":
                        respond("application/json", "website/json/data.json");
                        break;

                    default:
                        respond("text/html", "website/pages/error.html");
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clientSocket.close();
            }

        }

    }


    public void respond(String contentType, String location) throws FileNotFoundException, IOException {

        File htmlFile = new File(location);
        //String file = readFile(htmlFile);
        Path path = Paths.get(location);
        Files.readAllBytes(path);

        outputStream.writeBytes("HTTP/1.1 200 OK\r\n");
        outputStream.writeBytes("Content-Type: " + contentType + "\r\n");

        outputStream.writeBytes("Content-Length: " + Files.readAllBytes(path).length + "\r\n");
        outputStream.writeBytes("\r\n");
        outputStream.write(Files.readAllBytes(path));
        outputStream.flush();
    }

}
