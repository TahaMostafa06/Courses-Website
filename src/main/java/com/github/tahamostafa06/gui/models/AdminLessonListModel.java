package com.github.tahamostafa06.gui.models;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.api.Admin;
import com.github.tahamostafa06.backend.courseservice.CourseItem;
import com.github.tahamostafa06.backend.courseservice.LessonItem;

public class AdminLessonListModel extends AbstractListModel<LessonItem> {
    private Admin admin;
    private CourseItem courseItem;

    public AdminLessonListModel(Admin admin, CourseItem courseItem) {
        this.admin = admin;
        this.courseItem = courseItem;
    }

    @Override
    public LessonItem getElementAt(int index) {
        return admin.getLessons(courseItem).get(index);
    }

    @Override
    public int getSize() {
        return admin.getLessons(courseItem).size();
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setCourseItem(CourseItem courseItem) {
        this.courseItem = courseItem;
    }

    public void update() {
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
