package com.github.tahamostafa06.backend.database.coursedatabase;

public class Quiz {
    int maxRetries;
    Question[] questions;

    public Question getQuestion(String questionId) {
        for (var question : questions) {
            if (question.question.equals(questionId)) {
                return question;
            }
        }
        return null;
    }
}