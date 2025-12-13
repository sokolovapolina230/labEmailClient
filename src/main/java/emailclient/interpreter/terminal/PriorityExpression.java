package emailclient.interpreter.terminal;

import emailclient.interpreter.Expression;
import emailclient.model.Message;
import emailclient.model.enums.Priority;

public class PriorityExpression implements Expression {

    private final Priority priority;

    public PriorityExpression(String p) {
        this.priority = Priority.valueOf(p.toUpperCase());
    }

    @Override
    public boolean interpret(Message msg) {
        return msg.getPriority() == priority;
    }
}
