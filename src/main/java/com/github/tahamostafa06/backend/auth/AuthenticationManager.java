package com.github.tahamostafa06.backend.auth;

import java.util.ArrayList;

public class AuthenticationManager {
    private ArrayList<LoginToken> grantedTokens;

    public AuthenticationManager() {
        this.grantedTokens = new ArrayList<>();
    }

    void addToken(LoginToken token) {
        this.grantedTokens.add(token);
    }

    void removeToken(LoginToken token) {
        this.grantedTokens.remove(token);
    }

    public boolean authenticate(LoginToken token) {
        return this.grantedTokens.contains(token);
    }

    public boolean authenticate(LoginToken token, String role) {
        return this.grantedTokens.contains(token) && token.getRole().equals(role);
    }
}
