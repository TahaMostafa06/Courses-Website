package com.github.tahamostafa06.backend.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.github.tahamostafa06.backend.api.Instructor;
import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.api.UserApi;
import com.github.tahamostafa06.backend.auth.Exceptions.IncorrectPassword;
import com.github.tahamostafa06.backend.auth.Exceptions.UserNotFound;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.database.userdatabase.UserDatabase;

public class AuthenticationHelper {
    private UserDatabase userDb;
    private CourseService courseService;
    private AuthenticationManager authenticationManager;

    public AuthenticationHelper(UserDatabase userDb, CourseService courseService,
            AuthenticationManager authenticationManager) {
        this.userDb = userDb;
        this.courseService = courseService;
        this.authenticationManager = authenticationManager;
    }

    public static String sha256(String input) {
        try {
            var hasher = MessageDigest.getInstance("SHA-256");
            var hashedBytes = hasher.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    public UserApi login(String username, String password) throws UserNotFound, IncorrectPassword {
        var user = userDb.getUserByUsername(username);
        if (user == null) {
            throw new UserNotFound("No user with " + username + " was found.");
        }
        if (!user.getPasswordHash().equals(sha256(password))) {
            throw new IncorrectPassword("Incorrect password.");
        }
        var userId = this.userDb.getIdByRecord(user);
        var token = new LoginToken(user.getRole(), userId);
        this.authenticationManager.addToken(token);
        if (user.getRole().equals("Instructor"))
            return new Instructor(token, this.courseService);
        else
            return new Student(token, this.courseService);
    }

    public UserApi signUp(String role, String username, String email, String password) {
        var passwordHash = sha256(password);
        var user = this.userDb.addUser(role, username, email, passwordHash);
        var userId = this.userDb.getIdByRecord(user);
        var token = new LoginToken(user.getRole(), userId);
        this.authenticationManager.addToken(token);
        if (user.getRole().equals("Instructor"))
            return new Instructor(token, this.courseService);
        else
            return new Student(token, this.courseService);
    }
}
