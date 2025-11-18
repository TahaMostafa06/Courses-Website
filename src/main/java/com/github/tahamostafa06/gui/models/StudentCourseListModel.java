package com.github.tahamostafa06.gui.models;

import java.util.List;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.StudentCourseItem;

public class StudentCourseListModel extends AbstractListModel<StudentCourseItem> {
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

    private List<StudentCourseItem> getCourses() {
        if (filter == EnrollmentFilter.AVAILABLE)
            return student.getAvailableCourses();
        else
            return student.getEnrolledCourses();
    }

    @Override
    public StudentCourseItem getElementAt(int index) {
        return getCourses().get(index);
    }

    @Override
    public int getSize() {
        return getCourses().size();
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
