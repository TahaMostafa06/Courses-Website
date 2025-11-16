package com.github.tahamostafa06.backend.database.coursedatabase;

import java.util.ArrayList;

public class Lesson {
    private String title;
    private String content;
    private ArrayList<String> optionalResources;

    private Lesson() {
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
}
