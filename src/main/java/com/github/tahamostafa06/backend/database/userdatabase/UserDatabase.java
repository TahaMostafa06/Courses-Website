package com.github.tahamostafa06.backend.database.userdatabase;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import com.github.tahamostafa06.backend.database.common.JsonDatabase;
import com.google.gson.reflect.TypeToken;

public class UserDatabase extends JsonDatabase<User> {

    public UserDatabase() throws IOException {
        super("Users.json", new TypeToken<Map<String, User>>() {
        });

    }

    public User addUser(String role, String username, String email, String passwordHash) {
        var user = new User(role, username, email, passwordHash);
        this.addRecord(user);
        return user;
    }

    @Override
    public String generateNewId(User user) {
        String idPrefix = "";
        if (user.getRole().equals("Student")) {
            idPrefix = "S-";
        } else if (user.getRole().equals("Instructor")) {
            idPrefix = "I-";
        }
        var generator = new Random();
        String id;
        var keys = this.records.keySet();
        while (true) {
            id = idPrefix + generator.nextLong(1000000, 9999999);
            if (!keys.contains(id))
                return id;
        }
    }

    public User getUserByUserId(String userId) {
        return this.getRecord(userId);
    }

    public User getUserByUsername(String username) {
        for (var user : this.records.values()) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (var user : this.records.values()) {
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    public String getRoleByUsername(String username) {
        for (var user : this.records.values()) {
            if (user.getUsername().equals(username))
                return user.getRole();
        }
        return null;
    }

}
