package com.dxc.fresher.authservice.models;

public class UserModel {

    private String userName;
    private String passWord;
    private String role;
    private String level;


    public UserModel() {
    }

    public UserModel(String userName, String passWord, String role, String level) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.level = level;
    }

    public UserModel(String userName, String passWord, String role) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
