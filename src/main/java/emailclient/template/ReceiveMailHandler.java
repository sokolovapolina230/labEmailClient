package emailclient.template;

import emailclient.model.Account;
import emailclient.model.Message;
import java.util.List;

public abstract class ReceiveMailHandler {

    public final List<Message> process(Account account) {
        connect(account);
        authenticate(account);
        List<Message> messages = fetchMessages(account);
        disconnect();
        return messages;
    }

    protected abstract void connect(Account acc);
    protected abstract void authenticate(Account acc);
    protected abstract List<Message> fetchMessages(Account acc);
    protected abstract void disconnect();
}
