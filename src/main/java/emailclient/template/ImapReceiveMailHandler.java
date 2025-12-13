package emailclient.template;

import emailclient.model.Account;
import emailclient.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ImapReceiveMailHandler extends ReceiveMailHandler {

    @Override
    protected void connect(Account acc) {
        System.out.println("IMAP: Connecting");
    }

    @Override
    protected void authenticate(Account acc) {
        System.out.println("IMAP: Authenticating " + acc.getEmail());
    }

    @Override
    protected List<Message> fetchMessages(Account acc) {
        System.out.println("IMAP: Synchronizing folders");
        return new ArrayList<>();
    }

    @Override
    protected void disconnect() {
        System.out.println("IMAP: Disconnect");
    }
}
