package com.github.tahamostafa06.backend.courseservice;

import com.github.tahamostafa06.backend.database.coursedatabase.Course;

public class StudentCourseItem {
    private final Course course;

    public StudentCourseItem(Course course) {
        this.course = course;
    }

    Course getCourse() {
        return course;
    }

    public String getTitle() {
        return course.getTitle();
    }

    public String getDescription() {
        return course.getDescription();
    }

    @Override
    public String toString() {
        return course.getTitle();
    }
}
