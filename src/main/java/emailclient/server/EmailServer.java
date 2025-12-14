package emailclient.server;

import emailclient.service.MessageService;

import java.net.ServerSocket;
import java.net.Socket;

public class EmailServer {

    private final MessageService messageService = new MessageService();

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket client = serverSocket.accept();
                new Thread(new ServerWorker(client, messageService)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EmailServer().start(5555);
    }
}
