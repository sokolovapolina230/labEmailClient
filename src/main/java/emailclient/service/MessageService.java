package emailclient.service;

import emailclient.decorator.*;
import emailclient.interpreter.Expression;
import emailclient.interpreter.FilterContext;
import emailclient.interpreter.QueryParser;
import emailclient.model.Account;
import emailclient.model.Message;
import emailclient.model.enums.ProtocolType;
import emailclient.repository.AccountRepository;
import emailclient.repository.MessageRepository;
import emailclient.template.ReceiveMailHandler;
import emailclient.template.Pop3ReceiveMailHandler;
import emailclient.template.ImapReceiveMailHandler;

import java.util.List;

public class MessageService {

    private final MessageRepository messageRepository = new MessageRepository();
    private final AccountRepository accountRepository = new AccountRepository();

    // Send
    public void send(Message message, boolean sign, boolean markImportant) {

        // Decorator
        MessageProcessor processor = new BasicMessageProcessor();

        if (sign) processor = new SignatureDecorator(processor);
        if (markImportant) processor = new ImportantDecorator(processor);

        String newBody = processor.process(message.getBody());

        Message processed = Message.builder()
                .id(message.getId())
                .accountId(message.getAccountId())
                .sender(message.getSender())
                .recipient(message.getRecipient())
                .subject(message.getSubject())
                .body(newBody)
                .priority(message.getPriority())
                .attachments(message.getAttachments())
                .build();

        Account acc = accountRepository.getById(processed.getAccountId());

        if (acc.getProtocol() != ProtocolType.SMTP)
            throw new IllegalArgumentException("SMTP required for sending.");

        sendViaSmtp(processed, acc);

        messageRepository.add(processed);
    }

    private void sendViaSmtp(Message msg, Account acc) {
        System.out.println("SMTP: connecting");
        System.out.println("SMTP: authenticating " + acc.getEmail());
        System.out.println("SMTP: sending to " + msg.getRecipient());
        System.out.println("SMTP: disconnect");
    }

    // Receive
    public List<Message> receive(int accountId) {

        Account acc = accountRepository.getById(accountId);

        ReceiveMailHandler handler = switch (acc.getProtocol()) {
            case POP3 -> new Pop3ReceiveMailHandler();
            case IMAP -> new ImapReceiveMailHandler();
            case SMTP -> throw new IllegalArgumentException(
                    "SMTP cannot receive messages.");
        };

        return handler.process(acc);
    }

    public List<Message> filter(String query) {
        List<Message> all = messageRepository.getAll();

        QueryParser parser = new QueryParser();
        Expression expr = parser.parse(query);

        FilterContext ctx = new FilterContext(all);

        return ctx.filter(expr);
    }

}
