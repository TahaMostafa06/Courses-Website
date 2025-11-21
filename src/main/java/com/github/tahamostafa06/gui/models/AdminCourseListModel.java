package com.github.tahamostafa06.gui.models;

import java.util.List;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.api.Admin;
import com.github.tahamostafa06.backend.courseservice.CourseItem;

public class AdminCourseListModel extends AbstractListModel<CourseItem> {
    private Admin admin;
    private StatusFilter filter;
    
    public enum StatusFilter {
        PENDING,
        ALL;
    }
    
    public AdminCourseListModel(Admin admin, StatusFilter defaultFilter) {
        this.filter = defaultFilter;
        this.admin = admin;
    }

    private List<CourseItem> getCourses() {
        if (filter == StatusFilter.PENDING)
            return admin.getPendingCourses();
        else
            return admin.getAllCourses();
    }
    
    @Override
    public CourseItem getElementAt(int index) {
        return getCourses().get(index);
    }

    @Override
    public int getSize() {
        return admin.getAllCourses().size();
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        update();
    }
    
    public void setFilter(StatusFilter filter) {
        this.filter = filter;
        update();
    }
    
    public void update() {
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
