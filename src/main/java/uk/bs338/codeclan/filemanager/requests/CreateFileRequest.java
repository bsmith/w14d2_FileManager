package uk.bs338.codeclan.filemanager.requests;

public class CreateFileRequest {
    private String name;
    private String extension;
    private long size;
    private long folderId;

    public CreateFileRequest() { }

    public CreateFileRequest(String name, String extension, long size, long folderId) {
        this.name = name;
        this.extension = extension;
        this.size = size;
        this.folderId = folderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getFolderId() {
        return folderId;
    }

    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }
}
