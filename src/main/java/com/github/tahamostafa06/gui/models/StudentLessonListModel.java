package com.github.tahamostafa06.gui.models;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.courseservice.LessonItem;

public class StudentLessonListModel extends AbstractListModel<LessonItem> {

    private Student student;
    private CourseItem courseItem;

    public StudentLessonListModel(Student student, CourseItem courseItem) {
        this.student = student;
        this.courseItem = courseItem;
    }

    @Override
    public LessonItem getElementAt(int index) {
        return student.getLessons(courseItem).get(index);
    }

    @Override
    public int getSize() {
        return student.getLessons(courseItem).size();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourseItem(CourseItem courseItem) {
        this.courseItem = courseItem;
    }

    public void update() {
        fireContentsChanged(this, 0, getSize() - 1);
    }

}
