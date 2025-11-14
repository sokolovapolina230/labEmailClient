package emailclient.service;

import emailclient.model.Message;
import emailclient.repository.MessageRepository;
import emailclient.decorator.*;

public class MessageService {

    private final MessageRepository messageRepository = new MessageRepository();

    public void send(Message message,
                     boolean encrypt,
                     boolean sign,
                     boolean markImportant) {

        // Базовий процесор
        MessageProcessor processor = new BasicMessageProcessor();

        // Декоратори за умовою
        if (sign) {
            processor = new SignatureDecorator(processor);
        }

        if (markImportant) {
            processor = new ImportantDecorator(processor);
        }

        String processedBody = processor.process(message.getBody());

        // Створення копії Message з новим текстом через Builder
        Message processedMessage = Message.builder()
                .id(message.getId())
                .accountId(message.getAccountId())
                .sender(message.getSender())
                .recipient(message.getRecipient())
                .subject(message.getSubject())
                .body(processedBody)
                .priority(message.getPriority())
                .attachments(message.getAttachments())
                .build();

        // Зберегається в БД (імітація відправки)
        messageRepository.add(processedMessage);
    }
}
