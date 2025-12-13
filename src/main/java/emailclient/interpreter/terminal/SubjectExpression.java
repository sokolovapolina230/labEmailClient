package emailclient.interpreter.terminal;

import emailclient.interpreter.Expression;
import emailclient.model.Message;

public class SubjectExpression implements Expression {

    private final String text;

    public SubjectExpression(String text) {
        this.text = text.toLowerCase();
    }

    @Override
    public boolean interpret(Message msg) {
        return msg.getSubject() != null &&
                msg.getSubject().toLowerCase().contains(text);
    }
}
