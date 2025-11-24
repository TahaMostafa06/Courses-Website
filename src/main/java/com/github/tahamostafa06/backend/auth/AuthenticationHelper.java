package com.github.tahamostafa06.backend.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.github.tahamostafa06.backend.api.Admin;
import com.github.tahamostafa06.backend.api.Instructor;
import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.api.UserApi;
import com.github.tahamostafa06.backend.auth.Exceptions.EmailAlreadyInUse;
import com.github.tahamostafa06.backend.auth.Exceptions.IncorrectPassword;
import com.github.tahamostafa06.backend.auth.Exceptions.UserAlreadyExists;
import com.github.tahamostafa06.backend.auth.Exceptions.UserNotFound;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.database.userdatabase.UserDatabase;
import com.github.tahamostafa06.backend.userservice.UserService;

public class AuthenticationHelper {
    private UserDatabase userDb;
    private CourseService courseService;
    private UserService userService;
    private AuthenticationManager authenticationManager;

    public AuthenticationHelper(UserDatabase userDb, CourseService courseService,
            UserService userService, AuthenticationManager authenticationManager) {
        this.userDb = userDb;
        this.userService = userService;
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
        var userId = userDb.getIdByRecord(user);
        var token = new LoginToken(user.getRole(), userId);
        authenticationManager.addToken(token);
        if (user.getRole().equals("Instructor")){
            return new Instructor(token, courseService, userService);
        }else if(user.getRole().equals("Student")){
            return new Student(token, courseService, userService);
        }else{
            return new Admin(token, courseService, userService); //change student to admin
        }
    }

    public UserApi signUp(String role, String username, String email, String password) throws UserAlreadyExists, EmailAlreadyInUse {
        if (userDb.getUserByUsername(username) != null)
            throw new UserAlreadyExists("User with username " + username + " already exists");
        if (userDb.getUserByEmail(email) != null)
            throw new EmailAlreadyInUse("Email " + email + " is already in use by another user.");
        var passwordHash = sha256(password);
        var user = userDb.addUser(role, username, email, passwordHash);
        var userId = userDb.getIdByRecord(user);
        var token = new LoginToken(user.getRole(), userId);
        authenticationManager.addToken(token);
        if (user.getRole().equals("Instructor")){
            return new Instructor(token, courseService, userService);
        }else if(user.getRole().equals("Student")){
            return new Student(token, courseService, userService);
        }else{
            return new Admin(token, courseService, userService); //change student to admin
        }
    }
}
