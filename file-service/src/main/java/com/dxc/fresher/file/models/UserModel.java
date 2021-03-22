package com.dxc.fresher.file.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

public class UserModel {

    private int id;
    private String userName;
    private String passWord;
    private String role;
    private String level;
    private Long dailyLimit;
    private LocalDate date;

    public UserModel() {
    }

    public UserModel(int id, String userName, String passWord, String role, String level) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.level = level;
    }

    public UserModel(String userName, String passWord, String role, String level) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.level = level;
    }

    public UserModel(int id, String userName, String passWord, String role, String level, Long dailyLimit, LocalDate date) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.level = level;
        this.dailyLimit = dailyLimit;
        this.date = date;
    }

    public UserModel(String userName, Long dailyLimit, LocalDate date) {
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

    public Long getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Long dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
