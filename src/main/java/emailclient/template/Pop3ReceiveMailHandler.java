package emailclient.template;

import emailclient.model.Account;
import emailclient.model.Message;

import java.util.ArrayList;
import java.util.List;

public class Pop3ReceiveMailHandler extends ReceiveMailHandler {

    @Override
    protected void connect(Account acc) {
        System.out.println("POP3: Connecting to server");
    }

    @Override
    protected void authenticate(Account acc) {
        System.out.println("POP3: Authenticating " + acc.getEmail());
    }

    @Override
    protected List<Message> fetchMessages(Account acc) {
        System.out.println("POP3: Downloading all messages");
        return new ArrayList<>();
    }

    @Override
    protected void disconnect() {
        System.out.println("POP3: Disconnect");
    }
}
