package com.dxc.fresher.user.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name="LEVEL")
    private String level;

    @Column(name="USERNAME",unique = true)
    private String userName;

    @Column(name="EMAIL", unique = true)
    private String email;

    @Column(name="PASSWORD")
    private String passWord;

    @Column(name="ROLE")
    private  String role;

    @Column(name="DAILYLIMIT")
    private long dailyLimit;

    @Column(name="DATE")
    private LocalDate date;

    public User() {
    }

    //---
    public User(int id,long dailyLimit,LocalDate date, String level, String userName, String email, String passWord, String role) {
        this.id = id;
        this.dailyLimit=dailyLimit;
        this.date=date;
        this.level = level;
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
        this.role = role;
    }

    public User(String userName, String passWord, String role) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public User(String userName, long dailyLimit, LocalDate date) {
        this.userName = userName;
        this.dailyLimit = dailyLimit;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(long dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
