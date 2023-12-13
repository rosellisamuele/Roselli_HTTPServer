package httpserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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

                    default:
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clientSocket.close();
            }

        }

    }

    public String readFile(File htmlFile) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(htmlFile));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }

    public void respond(String contentType, String filePath) throws FileNotFoundException, IOException {

        File htmlFile = new File(filePath);
        String file = readFile(htmlFile);

        outputStream.writeBytes("HTTP/1.1 200 OK\r\n");
        outputStream.writeBytes("Content-Type: " + contentType + "\r\n");

        outputStream.writeBytes("Content-Length: " + file.getBytes().length + "\r\n");
        outputStream.writeBytes("\r\n");
        outputStream.writeBytes(file);
        outputStream.flush();
    }

}
