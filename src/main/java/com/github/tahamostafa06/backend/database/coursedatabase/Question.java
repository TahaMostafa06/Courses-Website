package com.github.tahamostafa06.backend.database.coursedatabase;

public class Question {
    String question;
    String[] choices;
    String correctAnswer;
    public String getQuestion() {
        return question;
    }
    public String[] getChoices() {
        return choices;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}