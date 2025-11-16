package com.github.tahamostafa06.backend.courseservice;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.database.coursedatabase.CourseDatabase;

public class CourseService {
    private CourseDatabase courseDb;

    public CourseService(CourseDatabase courseDb) {
        this.courseDb = courseDb;
    }

    private static boolean authenticateStudent(LoginToken token) {
        return token.getRole().equals("Student");
    }

    private static boolean authenticateInstructor(LoginToken token) {
        return token.getRole().equals("Instructor");
    }

    public void enroll(LoginToken token, String courseId) {
        if (!authenticateStudent(token))
            return;
        if (courseDb.isStudentEnrolledIn(courseId, token.getUserId()))
            return;
        this.courseDb.enroll(courseId, token.getUserId());
    }

}
