package com.phuocthuy.model.request;

import java.util.Date;

// DTO để nhận request
public class ImageUploadRequest {
    private String file;  // Base64 string
    private String owner;
    private boolean isPublic;
    private Date createdAt;
    private String classOfImage; // anh san pham, anh khach hang, anh nhan vien, anh khac

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getClassOfImage() {
        return classOfImage;
    }

    public void setClassOfImage(String classOfImage) {
        this.classOfImage = classOfImage;
    }
}