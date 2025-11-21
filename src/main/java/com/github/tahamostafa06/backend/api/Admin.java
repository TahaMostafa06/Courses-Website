package com.github.tahamostafa06.backend.api;

import java.util.List;

import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.courseservice.CourseService;
import com.github.tahamostafa06.backend.courseservice.LessonItem;
import com.github.tahamostafa06.backend.userservice.UserService;

public class Admin extends UserApi{
    
    public Admin(LoginToken accessToken, CourseService courseService, UserService userService) {
        super(accessToken, courseService, userService);
    }
    public List<CourseItem> getAllCourses() {
        return courseService.getAllCourses(accessToken);
    }
    
    public List<CourseItem> getPendingCourses() {
        return courseService.getPendingCourses(accessToken);
    }
    
    public void setCourseDescription(CourseItem courseItem, String newDescription) {
        courseService.setCourseDescription(accessToken, courseItem, newDescription);
    }

    public void setCourseTitle(CourseItem courseItem, String newTitle) {
        courseService.setCourseTitle(accessToken, courseItem, newTitle);
    }
    public LessonItem addLesson(CourseItem courseItem, String title, String content,
            String additionalResources) {
        return courseService.addLesson(accessToken, courseItem, title, content,
                List.of(additionalResources.split("(\\s*),(\\s*)")));
    }

    public void removeLesson(CourseItem courseItem, LessonItem lessonItem) {
        courseService.removeLesson(accessToken, courseItem, lessonItem);
    }

    public void setLessonTitle(CourseItem courseItem, LessonItem lessonItem, String title) {
        courseService.setLessonTitle(accessToken, courseItem, lessonItem, title);
    }

    public void setLessonContent(CourseItem courseItem, LessonItem lessonItem, String content) {
        courseService.setLessonContent(accessToken, courseItem, lessonItem, content);
    }

    public void setLessonAdditionalResources(CourseItem courseItem, LessonItem lessonItem,
            String additionalResources) {
        courseService.setLessonAdditionalResources(accessToken, courseItem, lessonItem,
                List.of(additionalResources.split(",")));
    }
    
    public void setCourseInstructor(CourseItem courseItem, String Instructor) {
        courseService.setCourseInstructor(accessToken, courseItem, Instructor);
    }
    
    public CourseItem createCourse(String title, String description, String Instructor) {
        return courseService.createCourse(accessToken, title, description, Instructor);
    }

    public void deleteCourse(CourseItem courseItem) {
        courseService.deleteCourse(accessToken, courseItem);
    }
}
