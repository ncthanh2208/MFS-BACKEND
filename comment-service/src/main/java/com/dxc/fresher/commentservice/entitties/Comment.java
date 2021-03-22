package com.dxc.fresher.commentservice.entitties;


import javax.persistence.*;

@Entity
@Table(name="COMMENT")
public class Comment {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name="USERNAME")
    private String userName;

    @Column(name="FILEID")
    private int fileId;

    @Column(name ="CONTENT")
    private String content;

    public Comment() {

    }

    public Comment(String userName, int fileId, String content) {
        this.userName = userName;
        this.fileId = fileId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
