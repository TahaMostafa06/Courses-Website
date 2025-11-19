package com.github.tahamostafa06.backend.courseservice;

import java.util.Objects;

import com.github.tahamostafa06.backend.database.coursedatabase.Course;

public class CourseItem {
    private final Course course;

    public CourseItem(Course course) {
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

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        CourseItem otherItem = (CourseItem) otherObject;
        // Assuming you have a unique ID field. If not, compare title/description
        return this.course == otherItem.course;
    }
}
