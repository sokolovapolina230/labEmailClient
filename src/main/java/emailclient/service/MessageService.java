package emailclient.service;

import emailclient.decorator.*;
import emailclient.interpreter.Expression;
import emailclient.interpreter.FilterContext;
import emailclient.interpreter.QueryParser;
import emailclient.model.Account;
import emailclient.model.Message;
import emailclient.model.enums.ProtocolType;
import emailclient.network.EmailClient;
import emailclient.network.Request;
import emailclient.network.Response;
import emailclient.repository.AccountRepository;
import emailclient.repository.MessageRepository;
import emailclient.template.ReceiveMailHandler;
import emailclient.template.Pop3ReceiveMailHandler;
import emailclient.template.ImapReceiveMailHandler;

import java.util.List;

public class MessageService {

    private final MessageRepository messageRepository = new MessageRepository();
    private final AccountRepository accountRepository = new AccountRepository();
    private final EmailClient networkClient = new EmailClient("localhost", 5555);


    // Send
    public void send(Message msg, boolean sign, boolean important) {

        // локальна обробка Decorator
        MessageProcessor processor = new BasicMessageProcessor();
        if (sign) processor = new SignatureDecorator(processor);
        if (important) processor = new ImportantDecorator(processor);

        Message processed = Message.builder()
                .id(msg.getId())
                .accountId(msg.getAccountId())
                .sender(msg.getSender())
                .recipient(msg.getRecipient())
                .subject(msg.getSubject())
                .body(processor.process(msg.getBody()))
                .priority(msg.getPriority())
                .attachments(msg.getAttachments())
                .build();

        // надсилаємо на сервер

        Request req = new Request(Request.Type.SEND_MESSAGE);
        req.put("msg", processed);

        Response resp = networkClient.sendRequest(req);

        System.out.println("SERVER RESPONSE: " + resp.getMessage());
    }


    private void sendViaSmtp(Message msg, Account acc) {
        System.out.println("SMTP: connecting");
        System.out.println("SMTP: authenticating " + acc.getEmail());
        System.out.println("SMTP: sending to " + msg.getRecipient());
        System.out.println("SMTP: disconnect");
    }

    // Receive
    public List<Message> receive(int accId) {

        Request req = new Request(Request.Type.GET_MESSAGES);
        req.put("accountId", accId);

        Response resp = networkClient.sendRequest(req);

        return (List<Message>) resp.getData();
    }

    public List<Message> filter(String q) {

        Request req = new Request(Request.Type.FILTER_MESSAGES);
        req.put("query", q);

        Response resp = networkClient.sendRequest(req);

        return (List<Message>) resp.getData();
    }

}
