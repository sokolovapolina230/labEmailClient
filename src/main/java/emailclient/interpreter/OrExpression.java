package emailclient.interpreter;

import emailclient.model.Message;

public class OrExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public OrExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Message msg) {
        return left.interpret(msg) || right.interpret(msg);
    }
}
