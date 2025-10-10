package emailclient.model;

import emailclient.model.enums.Priority;
import java.util.List;

public class Message {
    private int id;
    private int accountId;
    private String sender;
    private String recipient;
    private String subject;
    private String body;
    private Priority priority;
    private List<Attachment> attachments;

    public Message() {}
    // геттери і сеттери
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public List<Attachment> getAttachments() { return attachments; }
    public void setAttachments(List<Attachment> attachments) { this.attachments = attachments; }
}
