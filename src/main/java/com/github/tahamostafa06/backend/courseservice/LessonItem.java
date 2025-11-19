package com.github.tahamostafa06.backend.courseservice;

import com.github.tahamostafa06.backend.database.coursedatabase.Lesson;

public class LessonItem {
    private final Lesson lesson;

    public LessonItem(Lesson lesson) {
        this.lesson = lesson;
    }

    Lesson getLesson() {
        return lesson;
    }

    public String getTitle() {
        return lesson.getTitle();
    }

    public String getContent() {
        return lesson.getContent();
    }

    public String getAdditionalResource() {
        return lesson.getOptionalResources().toString();
    }

    @Override
    public String toString() {
        return lesson.getTitle();
    }
}
