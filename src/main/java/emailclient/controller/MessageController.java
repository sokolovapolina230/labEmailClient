package emailclient.controller;

import emailclient.model.Message;
import emailclient.model.enums.FolderType;
import emailclient.model.enums.Priority;
import emailclient.service.MessageService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MessageController {

    @FXML private TextField txtRecipient;
    @FXML private TextField txtSubject;
    @FXML private TextArea txtBody;

    private final MessageService messageService = new MessageService();

    @FXML
    private void handleSend() {

        Message msg = Message.builder()
                .recipient(txtRecipient.getText())
                .subject(txtSubject.getText())
                .body(txtBody.getText())
                .priority(Priority.MEDIUM)
                .build();

        messageService.send(msg);
    }
}
