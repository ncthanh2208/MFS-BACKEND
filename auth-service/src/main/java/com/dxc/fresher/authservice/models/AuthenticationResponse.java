package com.dxc.fresher.authservice.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String token;
    private  String role;
    private String level;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public AuthenticationResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public AuthenticationResponse(String token, String role, String level) {
        this.token = token;
        this.role = role;
        this.level = level;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public String getLevel() {
        return level;
    }
}
