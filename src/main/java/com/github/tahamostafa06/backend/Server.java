package com.github.tahamostafa06.backend;

import com.github.tahamostafa06.backend.auth.AuthenticationHelper;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.database.coursedatabase.CourseDatabase;
import com.github.tahamostafa06.backend.database.userdatabase.UserDatabase;

// Initialize Databases
// A singleton
// Grant API access

public class Server {
    private static Server serverInstance = null;
    private UserDatabase userDb;
    private CourseDatabase courseDb;
    private AuthenticationHelper authHelper;
    private CourseService courseService;

    public static Server getServer() throws Exception {
        if (Server.serverInstance == null) {
            return new Server();
        } else {
            return Server.serverInstance;
        }
    }

    public AuthenticationHelper getAuthHelper() {
        return this.authHelper;
    }

    private Server() throws Exception {
        this.userDb = new UserDatabase();
        this.courseDb = new CourseDatabase();
        this.courseService = new CourseService(this.courseDb);
        this.authHelper = new AuthenticationHelper(this.userDb, this.courseService);
    }

}
