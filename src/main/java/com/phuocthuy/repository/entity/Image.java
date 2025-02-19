package com.phuocthuy.repository.entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
    private String owner; // Người tải ảnh lên
    private boolean isPublic; // Ảnh có công khai không?
    private LocalDateTime createdAt; // Ngày tải ảnh lên
    private String classOfImage; // anh san pham, anh khach hang, anh nhan vien, anh khac


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getClassOfImage() {
        return classOfImage;
    }

    public void setClassOfImage(String classOfImage) {
        this.classOfImage = classOfImage;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

}
