package emailclient.interpreter.terminal;

import emailclient.interpreter.Expression;
import emailclient.model.Message;

public class FromExpression implements Expression {

    private final String expected;

    public FromExpression(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean interpret(Message msg) {
        return msg.getSender() != null &&
                msg.getSender().equalsIgnoreCase(expected);
    }
}
