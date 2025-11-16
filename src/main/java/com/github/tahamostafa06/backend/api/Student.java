package com.github.tahamostafa06.backend.api;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseService;

public class Student extends UserApi {

    public Student(LoginToken accessToken, CourseService courseService) {
        super(accessToken, courseService);
    }

    public void enroll(String courseId) {
        courseService.enroll(this.accessToken, courseId);
    }

    public void viewAvailableCourses() {

    }

    public void viewAvailableCourses(boolean includeEnrolled) {
        
    }

    public void viewEnrolledCourses() {

    }

    public void viewLessons(String courseId) {

    }

    public void completeLesson(String lessonId) {

    }
}
