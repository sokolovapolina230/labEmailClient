package emailclient.model;

import emailclient.model.enums.FolderType;

public class Folder {
    private int id;
    private String name;
    private FolderType type;

    public Folder() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public FolderType getType() { return type; }
    public void setType(FolderType type) { this.type = type; }
}
