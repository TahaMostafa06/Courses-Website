package com.github.tahamostafa06.backend.courseservice;

import java.util.ArrayList;

import com.github.tahamostafa06.backend.database.coursedatabase.Lesson;
import com.github.tahamostafa06.backend.database.coursedatabase.Question;

public class LessonItem {
    private final Lesson lesson;

    public LessonItem(Lesson lesson) {
        this.lesson = lesson;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public String getTitle() {
        return lesson.getTitle();
    }

    public String getContent() {
        return lesson.getContent();
    }

    public String getAdditionalResource() {
        var resString = lesson.getOptionalResources().toString();
        return resString.substring(1, resString.length() - 1);
    }

    @Override
    public String toString() {
        return lesson.getTitle();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        LessonItem otherItem = (LessonItem) otherObject;
        // Assuming you have a unique ID field. If not, compare title/description
        return this.lesson == otherItem.lesson;
    }

    public ArrayList<Question> getQuiz() {
        return lesson.getQuiz();
    }
}
