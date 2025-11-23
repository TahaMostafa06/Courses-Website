package com.github.tahamostafa06.backend.database.coursedatabase;

import java.util.ArrayList;

public class StudentLessonProgress {
    ArrayList<ArrayList<String>> attemptsAnswers;
    ArrayList<ArrayList<String>> attemptsQuestions;
    ArrayList<ArrayList<Integer>> attemptsScores;
    boolean passed;

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public ArrayList<ArrayList<String>> getAttemptsAnswers() {
        return attemptsAnswers;
    }

    public ArrayList<ArrayList<String>> getAttemptsQuestions() {
        return attemptsQuestions;
    }

    public ArrayList<ArrayList<Integer>> getAttemptsScores() {
        return attemptsScores;
    }

}
