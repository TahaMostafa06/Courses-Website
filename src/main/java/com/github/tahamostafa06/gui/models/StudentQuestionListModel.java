package com.github.tahamostafa06.gui.models;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.courseservice.LessonItem;
import com.github.tahamostafa06.backend.courseservice.QuestionItem;

public class StudentQuestionListModel extends AbstractListModel<QuestionItem> {
    LessonItem lessonItem;
    ArrayList<QuestionItem> questionItems;

    public StudentQuestionListModel(LessonItem lessonItem) {
        this.lessonItem = lessonItem;
        updateQuestionItems();
    }

    void updateQuestionItems() {
        questionItems = new ArrayList<>();
        for (var question : lessonItem.getQuiz()) {
            questionItems.add(new QuestionItem(question));
        }
    }

    public void setLessonItem(LessonItem lessonItem) {
        this.lessonItem = lessonItem;
        updateQuestionItems();
    }

    @Override
    public QuestionItem getElementAt(int index) {
        return questionItems.get(index);
    }

    @Override
    public int getSize() {
        return questionItems.size();
    }

    public void markAsAnswered(int index, boolean answered) {
        getElementAt(index).markAnswered(answered);
        fireContentsChanged(this, 0, getSize() - 1);
    }
}
