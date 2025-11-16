package com.github.tahamostafa06.backend.courseservice;

import com.github.tahamostafa06.backend.auth.AuthenticationManager;
import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.database.coursedatabase.CourseDatabase;

public class CourseService {
    private CourseDatabase courseDb;
    private AuthenticationManager authenticationManager;

    public CourseService(CourseDatabase courseDb, AuthenticationManager authenticationManager) {
        this.courseDb = courseDb;
        this.authenticationManager = authenticationManager;
    }

    public void enroll(LoginToken token, String courseId) {
        if (this.authenticationManager.authenticate(token, "Student"))
            return;
        if (courseDb.isStudentEnrolledIn(courseId, token.getUserId()))
            return;
        this.courseDb.enroll(courseId, token.getUserId());
    }

}
