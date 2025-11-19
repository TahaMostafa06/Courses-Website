package com.github.tahamostafa06.gui.models;

import java.util.Objects;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.api.Instructor;
import com.github.tahamostafa06.backend.courseservice.CourseItem;

public class InstructorCourseListModel extends AbstractListModel<CourseItem> {
    private Instructor instructor;

    public InstructorCourseListModel(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public CourseItem getElementAt(int index) {
        return instructor.getMyCourses().get(index);
    }

    @Override
    public int getSize() {
        return instructor.getMyCourses().size();
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
        update();
    }

    public void update() {
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
