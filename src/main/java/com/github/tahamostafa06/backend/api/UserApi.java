package com.github.tahamostafa06.backend.api;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseService;

public abstract class UserApi {
    protected LoginToken accessToken;
    protected CourseService courseService;

    protected UserApi(LoginToken accessToken, CourseService courseService) {
        this.accessToken = accessToken;
        this.courseService = courseService;
    }
}
