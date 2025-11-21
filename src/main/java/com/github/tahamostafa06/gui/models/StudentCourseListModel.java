package com.github.tahamostafa06.gui.models;

import java.util.List;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.CourseItem;

public class StudentCourseListModel extends AbstractListModel<CourseItem> {
    private Student student;
    private EnrollmentFilter filter;

    public enum EnrollmentFilter {
        AVAILABLE,
        ENROLLED;
    }

    public StudentCourseListModel(Student student, EnrollmentFilter defaultFilter) {
        this.filter = defaultFilter;
        this.student = student;
    }

    private List<CourseItem> getCourses() {
        if (filter == EnrollmentFilter.AVAILABLE)
            return student.getAvailableCourses();
        else
            return student.getEnrolledCourses();
    }

    @Override
    public CourseItem getElementAt(int index) {
        return getCourses().get(index);
    }

    @Override
    public int getSize() {
        return this.getCourses().size();
    }

    public void setStudent(Student student) {
        this.student = student;
        update();
    }

    public void setFilter(EnrollmentFilter filter) {
        this.filter = filter;
        update();
    }

    public void update() {
        fireContentsChanged(this, 0, getSize() - 1);
    }

}
