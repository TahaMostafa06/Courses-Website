package com.github.tahamostafa06.backend.courseservice;

import java.util.ArrayList;
import java.util.List;

import com.github.tahamostafa06.backend.auth.AuthenticationManager;
import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.database.coursedatabase.CourseDatabase;
import com.github.tahamostafa06.backend.database.coursedatabase.Lesson;

public class CourseService {
    private CourseDatabase courseDb;
    private AuthenticationManager authenticationManager;

    public CourseService(CourseDatabase courseDb, AuthenticationManager authenticationManager) {
        this.courseDb = courseDb;
        this.authenticationManager = authenticationManager;
    }

    public void enroll(LoginToken token, String courseId) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        if (courseDb.isStudentEnrolledIn(courseId, token.getUserId()))
            return;
        this.courseDb.enroll(courseId, token.getUserId());
    }

    public void viewAvailableCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        var allCourses = courseDb.getAllCourses();
        var availableCourses = new ArrayList<String>();
        for (var courseId : allCourses) {
            if (!courseDb.isStudentEnrolledIn(courseId, token.getUserId())) {
                availableCourses.add(courseId);
            }
        }
    }

    public void getAllCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        var allCourses = courseDb.getAllCourses();
    }

    public void viewEnrolledCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        var allCourses = courseDb.getAllCourses();
        var availableCourses = new ArrayList<String>();
        for (var courseId : allCourses) {
            if (courseDb.isStudentEnrolledIn(courseId, token.getUserId())) {
                availableCourses.add(courseId);
            }
        }
    }

    public void getLessons(LoginToken token, String courseId) {
		if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        var allLessons=courseDb.getLessons(courseId);
	}

    //public void completeLesson(Lesson lessonToComplete, String studentID, String courseID)
    public void completeLesson(LoginToken token,String courseID,Lesson lessonToComplete) {
		if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        courseDb.completeLesson(lessonToComplete, token.getUserId(), courseID);
	}
}
