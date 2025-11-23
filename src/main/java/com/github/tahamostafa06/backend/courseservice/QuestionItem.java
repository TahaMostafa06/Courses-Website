package com.github.tahamostafa06.backend.courseservice;

import com.github.tahamostafa06.backend.database.coursedatabase.Question;

public class QuestionItem {
    private final Question question;
    private boolean answered;

    public QuestionItem(Question question) {
        this.question = question;
        this.answered = false;
    }

    public void markAnswered() {
        this.answered = true;
    }

    @Override
    public String toString() {
        if (!answered)
            return question.getQuestion();
        else
            return question.getQuestion() + " [answered]";
    }
}
