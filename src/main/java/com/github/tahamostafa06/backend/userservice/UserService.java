package com.github.tahamostafa06.backend.userservice;


import com.github.tahamostafa06.backend.auth.AuthenticationManager;
import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.database.userdatabase.UserDatabase;

public class UserService {
    private UserDatabase userDb;
    private AuthenticationManager authenticationManager;

    public UserService(UserDatabase userDb, AuthenticationManager authenticationManager) {
        this.userDb = userDb;
        this.authenticationManager = authenticationManager;
    }

    public String getUsernameById(String userId) {
        return userDb.getUserByUserId(userId).getUsername();
    }

    public String getUsernameByToken(LoginToken token) {
        return getUsernameById(token.getUserId());
    }
}
