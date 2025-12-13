package emailclient.interpreter;

import emailclient.model.Message;

public interface Expression {
    boolean interpret(Message msg);
}
