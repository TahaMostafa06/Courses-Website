package com.github.tahamostafa06.backend.api;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.userservice.UserService;

public abstract class UserApi {
    protected LoginToken accessToken;
    protected CourseService courseService;
    protected UserService userService;

    protected UserApi(LoginToken accessToken, CourseService courseService, UserService userService) {
        this.accessToken = accessToken;
        this.courseService = courseService;
        this.userService = userService;
    }
}
