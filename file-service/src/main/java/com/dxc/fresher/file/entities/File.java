package com.dxc.fresher.file.entities;

import javax.persistence.*;

@Entity
@Table(name ="FILE")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name = "DATA")
    @Lob
    private byte[] data;

    @Column(name="USERNAME")
    private String userName;

    @Column(name="NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "SIZE")
    private long size;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "CATEGORY")
    private String category;

    public File() {
    }

    public File(byte[] data, String userName, String name, String type, long size, String comment, String category) {
        this.data = data;
        this.userName = userName;
        this.name = name;
        this.type = type;
        this.size = size;
        this.comment = comment;
        this.category = category;
    }

    public File(String userName, String name, String type, long size, String comment, String category) {
        this.userName = userName;
       this.name = name;
        this.type = type;
        this.size = size;
        this.comment = comment;
        this.category = category;
    }

    public File(int id, String userName, String name, String type, long size, String comment, String category) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.type = type;
        this.size = size;
        this.comment = comment;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
