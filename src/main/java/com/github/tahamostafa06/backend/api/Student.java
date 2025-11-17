package com.github.tahamostafa06.backend.api;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.database.coursedatabase.Lesson;

public class Student extends UserApi {

    public Student(LoginToken accessToken, CourseService courseService) {
        super(accessToken, courseService);
    }

    public void enroll(String courseId) {
        courseService.enroll(this.accessToken, courseId);
    }

    public void viewAvailableCourses() {
        courseService.viewAvailableCourses(this.accessToken);
    }

    public void viewAllCourses() {
        courseService.getAllCourses(this.accessToken);
    }

    public void viewEnrolledCourses() {
        courseService.viewEnrolledCourses(this.accessToken);
    }

    public void viewLessons(String courseId) {
        courseService.getLessons(this.accessToken, courseId);
    }

    public void completeLesson(Lesson lessonId, String courseId) {
        courseService.completeLesson(this.accessToken, courseId, lessonId);
    }
}
