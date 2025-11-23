package com.github.tahamostafa06.backend.database.coursedatabase;

public class Quiz {
    int maxRetries;
    Question[] questions;

    public int getMaxRetries() {
        return maxRetries;
    }

    public Question[] getQuestions() {
        return questions;
    }

}