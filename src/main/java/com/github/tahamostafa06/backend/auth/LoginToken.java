package com.github.tahamostafa06.backend.auth;

public class LoginToken {
    private String role;
    private String userId;

    LoginToken(String role, String userId) {
        this.role = role;
        this.userId = userId;
    }

    public String getRole() {
        return this.role;
    }

    public String getUserId() {
        return this.userId;
    }

}
