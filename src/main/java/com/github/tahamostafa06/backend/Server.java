package com.github.tahamostafa06.backend;

import java.io.IOException;

import com.github.tahamostafa06.backend.auth.AuthenticationHelper;
import com.github.tahamostafa06.backend.auth.AuthenticationManager;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.database.coursedatabase.CourseDatabase;
import com.github.tahamostafa06.backend.database.userdatabase.UserDatabase;
import com.github.tahamostafa06.backend.userservice.UserService;

// Initialize Databases
// A singleton
// Grant API access

public class Server {
    private static Server serverInstance = null;
    private UserDatabase userDb;
    private CourseDatabase courseDb;
    private AuthenticationManager authenticationManager;
    private AuthenticationHelper authHelper;
    private CourseService courseService;
    private UserService userService;

    public static Server getServer() throws IOException {
        if (Server.serverInstance == null) {
            return new Server();
        } else {
            return Server.serverInstance;
        }
    }

    public AuthenticationHelper getAuthHelper() {
        return this.authHelper;
    }

    private Server() throws IOException {
        userDb = new UserDatabase();
        courseDb = new CourseDatabase();
        authenticationManager = new AuthenticationManager();
        userService = new UserService(userDb, authenticationManager);
        courseService = new CourseService(courseDb, userService, authenticationManager);
        authHelper = new AuthenticationHelper(userDb, courseService, userService, authenticationManager);
    }

    public void close() throws IOException {
        this.userDb.saveToFile();
        this.courseDb.saveToFile();
    }

}
