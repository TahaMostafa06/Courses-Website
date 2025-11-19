package com.github.tahamostafa06.backend.database.coursedatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.tahamostafa06.backend.database.common.Record;

public class Course implements Record {
    private String title;
    private String description;
    private String instructorId;
    private Map<String, Lesson> lessons;
    private Map<String, ArrayList<String>> students; // studentId : [doneLessonId1, doneLessonId2]

    Course(String instructorId, String title, String description) {
        this.title = title;
        this.instructorId = instructorId;
        this.description = description;
        lessons = new HashMap<>();
        students = new HashMap<>();
    }

    public Lesson addLesson(String title, String content, Collection<String> optionalResources) {
        var lesson = new Lesson(title, content, optionalResources);
        String idPrefix = "L-";
		var generator = new Random();
		String id;
		var keys = lessons.keySet();
		while (true) {
			id = idPrefix + generator.nextLong(1000000, 9999999);
			if (!keys.contains(id)) {
                lessons.put(id, lesson);
                return lesson;
            }
		}
    }

    public void removeLesson(Lesson lesson) {
        for (var l : lessons.entrySet()) {
            if (l.getValue() == lesson) {
                lessons.remove(l.getKey());
                return;
            }
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
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

