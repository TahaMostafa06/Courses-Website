package com.github.tahamostafa06.backend.api;

import java.util.List;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.courseservice.StudentCourseItem;
import com.github.tahamostafa06.backend.database.coursedatabase.Lesson;
import com.github.tahamostafa06.backend.userservice.UserService;

public class Student extends UserApi {

    public Student(LoginToken accessToken, CourseService courseService, UserService userService) {
        super(accessToken, courseService, userService);
    }

    public String getName() {
        return userService.getUsernameByToken(accessToken);
    }

    public void enroll(StudentCourseItem course) {
        courseService.enroll(accessToken, course);
    }

    public List<StudentCourseItem> getAvailableCourses() {
        return courseService.getAvailableCourses(accessToken);
    }

    public List<StudentCourseItem> getEnrolledCourses() {
        return courseService.getEnrolledCourses(accessToken);
    }

    public Integer getEnrolledStudentsCount(StudentCourseItem course) {
        return courseService.getEnrolledStudentCount(accessToken, course);
    }

    public String getInstructorName(StudentCourseItem course) {
        return courseService.getInstructorName(accessToken, course);
    }

    public Integer getLessonCount(StudentCourseItem course) {
        return courseService.getLessonCount(accessToken, course);
    }

    // TODO: revisit
    public void viewLessons(String courseId) {
        courseService.getLessons(accessToken, courseId);
    }

    public void completeLesson(Lesson lessonId, String courseId) {
        courseService.completeLesson(accessToken, courseId, lessonId);
    }
}
