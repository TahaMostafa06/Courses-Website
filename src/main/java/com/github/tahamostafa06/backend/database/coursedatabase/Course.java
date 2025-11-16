package com.github.tahamostafa06.backend.database.coursedatabase;

import java.util.ArrayList;
import java.util.Map;

import com.github.tahamostafa06.backend.database.common.Record;

public class Course implements Record {
    private String title;
    private String description;
    private String instructorId;
    private Map<String, Lesson> lessons;
    private Map<String, Integer> students;

    private Course() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public Map<String, Lesson> getLessons() {
        return lessons;
    }

    public Map<String, Integer> getStudentsAndProgress() {
        return students;
    }
}

class Lesson {
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