package com.github.tahamostafa06.backend.api;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.userservice.UserService;

public class Instructor extends UserApi {

    public Instructor(LoginToken accessToken, CourseService courseService, UserService userService) {
        super(accessToken, courseService, userService);
    }

}
