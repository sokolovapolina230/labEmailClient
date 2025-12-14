package emailclient.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class EmailClient {

    private final String host;
    private final int port;

    public EmailClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Response sendRequest(Request req) {
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(req);
            out.flush();

            return (Response) in.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, null, e.getMessage());
        }
    }
}
