package emailclient.controller;

import emailclient.model.Message;
import emailclient.model.enums.Priority;
import emailclient.service.MessageService;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MessageController {

    @FXML private TextField txtRecipient;
    @FXML private TextField txtSubject;
    @FXML private TextArea txtBody;

    @FXML private CheckBox chkEncrypt;
    @FXML private CheckBox chkSign;
    @FXML private CheckBox chkImportant;

    private final MessageService messageService = new MessageService();

    @FXML
    private void handleSend() {

        // Створення повідомлення через Builder
        Message msg = Message.builder()
                .recipient(txtRecipient.getText())
                .subject(txtSubject.getText())
                .body(txtBody.getText())
                .priority(chkImportant.isSelected() ? Priority.HIGH : Priority.MEDIUM)
                .build();

        // Виклик service з передачею прапорців Decorator
        messageService.send(
                msg,
                chkEncrypt.isSelected(),
                chkSign.isSelected(),
                chkImportant.isSelected()
        );

        System.out.println("Повідомлення надіслано!");
    }
}

