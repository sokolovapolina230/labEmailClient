package emailclient.interpreter;

import emailclient.model.Message;
import java.util.List;

public class FilterContext {

    private final List<Message> messages;

    public FilterContext(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> filter(Expression expr) {
        return messages.stream()
                .filter(expr::interpret)
                .toList();
    }
}
