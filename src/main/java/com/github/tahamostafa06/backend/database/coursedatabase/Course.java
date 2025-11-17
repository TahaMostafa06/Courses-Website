package com.github.tahamostafa06.backend.database.coursedatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.tahamostafa06.backend.database.common.Record;

public class Course implements Record {
    private String title;
    private String description;
    private String instructorId;
    private Map<String, Lesson> lessons;
    private Map<String, ArrayList<String>> students; // studentId : [doneLessonId1, doneLessonId2]

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

    public Map<String, ArrayList<String>> getStudentsAndLessonsDone() {
        return students;
    }

    public List<String> getStudents() {
        return List.copyOf(students.keySet());
    }
}

