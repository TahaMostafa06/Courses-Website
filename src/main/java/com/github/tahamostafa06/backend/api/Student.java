package com.github.tahamostafa06.backend.api;

import java.util.ArrayList;
import java.util.List;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.courseservice.LessonItem;
import com.github.tahamostafa06.backend.database.coursedatabase.Certificate;
import com.github.tahamostafa06.backend.database.coursedatabase.StudentLessonProgress;
import com.github.tahamostafa06.backend.userservice.UserService;

public class Student extends UserApi {

    public Student(LoginToken accessToken, CourseService courseService, UserService userService) {
        super(accessToken, courseService, userService);
    }

    public Certificate getCertificate(CourseItem course) {
        return courseService.getCertificate(accessToken, course);
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

    public void submitQuiz(CourseItem course, LessonItem lesson, ArrayList<String> answers) {
        courseService.submitQuiz(accessToken, course, lesson, answers);
    }

    public boolean isLessonDone(CourseItem course, LessonItem lesson) {
        return courseService.isLessonDone(accessToken, course, lesson);
    }

    public boolean isLessonAttempted(CourseItem course, LessonItem lesson) {
        return courseService.isLessonAttempted(accessToken, course, lesson);
    }

    public int getAttemptsCount(CourseItem course, LessonItem lesson) {
        return courseService.getAttemptsCount(accessToken, course, lesson);
    }

    public StudentLessonProgress getStudentLessonProgress(CourseItem courseItem,
            LessonItem lessonItem) {
        return courseService.getStudentLessonProgress(accessToken, courseItem, lessonItem);
    }

}
