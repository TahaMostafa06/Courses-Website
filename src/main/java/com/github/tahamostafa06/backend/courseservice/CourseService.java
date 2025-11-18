package com.github.tahamostafa06.backend.courseservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.github.tahamostafa06.backend.auth.AuthenticationManager;
import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.database.coursedatabase.Course;
import com.github.tahamostafa06.backend.database.coursedatabase.CourseDatabase;
import com.github.tahamostafa06.backend.database.coursedatabase.Lesson;
import com.github.tahamostafa06.backend.database.userdatabase.UserDatabase;
import com.github.tahamostafa06.backend.userservice.UserService;

public class CourseService {
    private CourseDatabase courseDb;
    private UserService userService;
    private AuthenticationManager authenticationManager;

    public CourseService(CourseDatabase courseDb, UserService userService, AuthenticationManager authenticationManager) {
        this.courseDb = courseDb;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    // Private methods

    private Set<String> getEnrolledStudents(Course course) {
        return course.getStudentsAndLessonsDone().keySet();
    }

    private Integer getEnrolledStudentsCount(Course course) {
        return course.getStudentsAndLessonsDone().keySet().size();
    }

    private Boolean isStudentEnrolledIn(Course courseId, String studentId) {
        return getEnrolledStudents(courseId).contains(studentId);
    }

    // Student API methods
    public void enroll(LoginToken token, StudentCourseItem courseItem) {
        var course = courseItem.getCourse();
        if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        if (isStudentEnrolledIn(course, token.getUserId()))
            return;
        course.getStudentsAndLessonsDone().put(token.getUserId(), new ArrayList<String>());
    }

    public List<StudentCourseItem> getAvailableCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var allCourses = courseDb.getAllCourses();
        var availableCourses = new ArrayList<StudentCourseItem>();
        for (var course : allCourses) {
            if (!isStudentEnrolledIn(course, token.getUserId())) {
                availableCourses.add(new StudentCourseItem(course));
            }
        }
        return availableCourses;
    }

    public List<StudentCourseItem> getEnrolledCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var allCourses = courseDb.getAllCourses();
        var enrolledCourses = new ArrayList<StudentCourseItem>();
        for (var course : allCourses) {
            if (isStudentEnrolledIn(course, token.getUserId())) {
                enrolledCourses.add(new StudentCourseItem(course));
            }
        }
        return enrolledCourses;
    }

    public Integer getEnrolledStudentCount(LoginToken token, StudentCourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var course = courseItem.getCourse();
        return getEnrolledStudentsCount(course);
    }

    public String getInstructorName(LoginToken token, StudentCourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var course = courseItem.getCourse();
        var instructorId = course.getInstructorId();
        return userService.getUsernameById(instructorId);
    }

    public Integer getLessonCount(LoginToken token, StudentCourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var course = courseItem.getCourse();
        return course.getLessons().size();
    }

    // TODO: revisit the following

    public void getLessons(LoginToken token, String courseId) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        var allLessons = courseDb.getLessons(courseId);
    }

    // public void completeLesson(Lesson lessonToComplete, String studentID, String
    // courseID)
    public void completeLesson(LoginToken token, String courseID, Lesson lessonToComplete) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        courseDb.completeLesson(lessonToComplete, token.getUserId(), courseID);
    }
}
