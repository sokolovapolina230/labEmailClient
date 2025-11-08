package emailclient.service;

import emailclient.model.Message;
import emailclient.repository.MessageRepository;

public class MessageService {

    private final MessageRepository messageRepository = new MessageRepository();

    public void send(Message message) {
        messageRepository.add(message);
    }
}
