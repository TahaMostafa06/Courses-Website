package com.github.tahamostafa06.backend.database.coursedatabase;

import java.util.ArrayList;
import java.util.Collection;

import org.jspecify.annotations.NullMarked;

public class Lesson {

    String title;
    String content;
    ArrayList<String> optionalResources;
    ArrayList<Question> quiz;
    int maxQuizRetries;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOptionalResources(Collection<String> optionalResources) {
        if (optionalResources == null)
            optionalResources = new ArrayList<String>();
        this.optionalResources = new ArrayList<String>(optionalResources);
    }

    @NullMarked
    Lesson(String title, String content, Collection<String> optionalResources, ArrayList<Question> quiz) {
        this.title = title;
        this.content = content;
        this.optionalResources = new ArrayList<String>(optionalResources);
        this.quiz = quiz;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<String> getOptionalResources() {
        return optionalResources;
    }

    public ArrayList<Question> getQuiz() {
        return quiz;
    }

}
