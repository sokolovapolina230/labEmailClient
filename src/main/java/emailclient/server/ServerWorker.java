package emailclient.server;

import emailclient.network.Request;
import emailclient.network.Response;
import emailclient.model.Message;
import emailclient.service.MessageService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ServerWorker implements Runnable {

    private final Socket socket;
    private final MessageService messageService;

    public ServerWorker(Socket socket, MessageService service) {
        this.socket = socket;
        this.messageService = service;
    }

    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            Request req = (Request) in.readObject();

            switch (req.getType()) {
                case SEND_MESSAGE -> {
                    Message m = (Message) req.get("msg");
                    messageService.send(m, false, false);
                    out.writeObject(new Response(true, null, "Message sent."));
                }
                case GET_MESSAGES -> {
                    int accId = (int) req.get("accountId");
                    List<Message> list = messageService.receive(accId);
                    out.writeObject(new Response(true, list, "Messages loaded."));
                }
                case FILTER_MESSAGES -> {
                    String q = (String) req.get("query");
                    List<Message> list = messageService.filter(q);
                    out.writeObject(new Response(true, list, "Filter OK."));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
