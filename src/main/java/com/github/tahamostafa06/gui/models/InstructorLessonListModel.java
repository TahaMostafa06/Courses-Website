package com.github.tahamostafa06.gui.models;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.api.Instructor;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.courseservice.LessonItem;

public class InstructorLessonListModel extends AbstractListModel<LessonItem> {

    private Instructor instructor;
    private CourseItem courseItem;

    public InstructorLessonListModel(Instructor instructor, CourseItem courseItem) {
        this.instructor = instructor;
        this.courseItem = courseItem;
    }

    @Override
    public LessonItem getElementAt(int index) {
        return instructor.getMyLessonsForCourse(courseItem).get(index);
    }

    @Override
    public int getSize() {
        return instructor.getMyLessonsForCourse(courseItem).size();
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setCourseItem(CourseItem courseItem) {
        this.courseItem = courseItem;
    }

    public void update() {
        fireContentsChanged(this, 0, getSize() - 1);
    }

}
