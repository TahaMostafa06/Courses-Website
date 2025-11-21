package com.github.tahamostafa06.gui.models;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.api.Admin;
import com.github.tahamostafa06.backend.courseservice.CourseItem;

public class AdminCourseListModel extends AbstractListModel<CourseItem> {
    private Admin admin;

    public AdminCourseListModel(Admin admin) {
        this.admin = admin;
    }

    @Override
    public CourseItem getElementAt(int index) {
        return admin.getAllCourses().get(index);
    }

    @Override
    public int getSize() {
        return admin.getAllCourses().size();
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        update();
    }

    public void update() {
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
