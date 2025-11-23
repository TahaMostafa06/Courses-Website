package com.github.tahamostafa06.backend.database.coursedatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Lesson {

    Quiz quiz;
    private String title;
    private String content;
    private ArrayList<String> optionalResources;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOptionalResources(Collection<String> optionalResources) {
        if (optionalResources == null) optionalResources = new ArrayList<String>();
        this.optionalResources = new ArrayList<String>(optionalResources);
    }

    Lesson(String title, String content, Collection<String> optionalResources,Quiz quiz) {
        if (title == null) title = "";
        this.title = title;
        if (content == null) content = "";
        this.content = content;
        if (optionalResources == null) optionalResources = new ArrayList<String>();
        this.optionalResources = new ArrayList<String>(optionalResources);
        if (quiz==null) quiz=new Quiz();
        this.quiz=quiz;
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

    public Quiz getQuiz() {
        return quiz;
    }
    
}
