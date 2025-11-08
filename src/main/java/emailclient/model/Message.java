package emailclient.model;

import emailclient.model.enums.Priority;
import java.util.List;

public class Message {

    private final int id;
    private final int accountId;
    private final String sender;
    private final String recipient;
    private final String subject;
    private final String body;
    private final Priority priority;
    private final List<Attachment> attachments;

    private Message(Builder builder) {
        this.id = builder.id;
        this.accountId = builder.accountId;
        this.sender = builder.sender;
        this.recipient = builder.recipient;
        this.subject = builder.subject;
        this.body = builder.body;
        this.priority = builder.priority;
        this.attachments = builder.attachments;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private int accountId;
        private String sender;
        private String recipient;
        private String subject;
        private String body;
        private Priority priority;
        private List<Attachment> attachments;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder accountId(int accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder attachments(List<Attachment> attachments) {
            this.attachments = attachments;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

    public int getId() { return id; }
    public int getAccountId() { return accountId; }
    public String getSender() { return sender; }
    public String getRecipient() { return recipient; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public Priority getPriority() { return priority; }
    public List<Attachment> getAttachments() { return attachments; }
}


