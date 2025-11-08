package emailclient.model;

public class Attachment {
    private int id;
    private int messageId;
    private String fileName;
    private byte[] data;

    public Attachment() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMessageId() { return messageId; }
    public void setMessageId(int messageId) { this.messageId = messageId; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }
}
