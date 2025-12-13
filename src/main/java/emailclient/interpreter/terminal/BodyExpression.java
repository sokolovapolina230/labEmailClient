package emailclient.interpreter.terminal;

import emailclient.interpreter.Expression;
import emailclient.model.Message;

public class BodyExpression implements Expression {

    private final String text;

    public BodyExpression(String text) {
        this.text = text.toLowerCase();
    }

    @Override
    public boolean interpret(Message msg) {
        return msg.getBody() != null &&
                msg.getBody().toLowerCase().contains(text);
    }
}
