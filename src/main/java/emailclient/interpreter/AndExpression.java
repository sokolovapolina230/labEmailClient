package emailclient.interpreter;

import emailclient.model.Message;

public class AndExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public AndExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Message msg) {
        return left.interpret(msg) && right.interpret(msg);
    }
}
