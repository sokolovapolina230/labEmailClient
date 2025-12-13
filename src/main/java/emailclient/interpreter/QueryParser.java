package emailclient.interpreter;

import emailclient.interpreter.terminal.*;

public class QueryParser {

    public Expression parse(String query) {
        query = query.trim().replace(" ", "");

        // AND
        if (query.contains("&")) {
            String[] parts = query.split("&", 2);
            return new AndExpression(parse(parts[0]), parse(parts[1]));
        }
        // OR
        if (query.contains("|")) {
            String[] parts = query.split("\\|", 2);
            return new OrExpression(parse(parts[0]), parse(parts[1]));
        }
        // Terminal rules
        if (query.startsWith("from:"))
            return new FromExpression(query.substring(5));

        if (query.startsWith("sub:"))
            return new SubjectExpression(query.substring(4));

        if (query.startsWith("body:"))
            return new BodyExpression(query.substring(5));

        if (query.startsWith("prio:"))
            return new PriorityExpression(query.substring(5));

        throw new IllegalArgumentException("Unknown expression: " + query);
    }
}
