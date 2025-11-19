package com.github.tahamostafa06.backend.api;

import java.util.List;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.courseservice.LessonItem;
import com.github.tahamostafa06.backend.userservice.UserService;

public class Student extends UserApi {

    public Student(LoginToken accessToken, CourseService courseService, UserService userService) {
        super(accessToken, courseService, userService);
    }

    public String getName() {
        return userService.getUsernameByToken(accessToken);
    }

    public void enroll(CourseItem course) {
        courseService.enroll(accessToken, course);
    }

    public List<CourseItem> getAvailableCourses() {
        return courseService.getAvailableCourses(accessToken);
    }

    public List<CourseItem> getEnrolledCourses() {
        return courseService.getEnrolledCourses(accessToken);
    }

    public Integer getEnrolledStudentsCount(CourseItem course) {
        return courseService.getEnrolledStudentCount(accessToken, course);
    }

    public String getInstructorName(CourseItem course) {
        return courseService.getInstructorName(accessToken, course);
    }

    public Integer getLessonCount(CourseItem course) {
        return courseService.getLessonCount(accessToken, course);
    }

    public Integer getFinishedLessonsCount(CourseItem course) {
        return courseService.getFinishedLessonsCount(accessToken, course);
    }

    public List<LessonItem> getLessons(CourseItem course) {
        return courseService.getLessons(accessToken, course);
    }

    public void completeLesson(CourseItem course, LessonItem lesson) {
        courseService.completeLesson(accessToken, course, lesson);
    }

    public boolean isLessonDone(CourseItem course, LessonItem lesson) {
        return courseService.isLessonDone(accessToken, course, lesson);
    }

}
