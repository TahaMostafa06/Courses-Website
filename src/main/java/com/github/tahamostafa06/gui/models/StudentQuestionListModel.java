package com.github.tahamostafa06.gui.models;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.github.tahamostafa06.backend.api.Student;
import com.github.tahamostafa06.backend.courseservice.QuestionItem;
import com.github.tahamostafa06.backend.database.coursedatabase.Question;
import com.github.tahamostafa06.backend.database.coursedatabase.Quiz;

public class StudentQuestionListModel extends AbstractListModel<QuestionItem> {
    Student student;
    Quiz quiz;
    ArrayList<QuestionItem> questionItems;

    public StudentQuestionListModel(Student student, Quiz quiz) {
        this.student = student;
        this.quiz = quiz;
        updateQuestionItems();
    }

    void updateQuestionItems() {
        questionItems = new ArrayList<>();
        for (var question : quiz.getQuestions()) {
            questionItems.add(new QuestionItem(question));
        }
    }

    public void setStudent(Student student) {
        this.student = student;
        updateQuestionItems();
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        updateQuestionItems();
    }

    @Override
    public QuestionItem getElementAt(int index) {
        return new QuestionItem(quiz.getQuestions()[index]);
    }

    @Override
    public int getSize() {
        return quiz.getQuestions().length;
    }

}
