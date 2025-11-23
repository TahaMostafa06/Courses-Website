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
    private String status;
    private Map<String, Lesson> lessons;
    private Map<String, ArrayList<String>> students;
    // studentId : [
    // "lessonId1" : {
    // },
    //  doneLessonId2]

    Course(String instructorId, String title, String description) {
        this.title = title;
        this.instructorId = instructorId;
        this.description = description;
        this.status = "PENDING";
        lessons = new HashMap<>();
        students = new HashMap<>();
    }
    
    Course(String instructorId, String title, String description, String Status) {
        this.title = title;
        this.instructorId = instructorId;
        this.description = description;
        this.status = Status;
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
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setInstructorId(String instructorId){
        this.instructorId = instructorId;
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

    public String getStatus() {
        return this.status;
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

    public ArrayList<Question> getQuizQuestions(Lesson lesson) {
        ArrayList<Question> quizQuestions=new ArrayList<Question>();
        for (var i : lesson.getQuiz().questions) {
            quizQuestions.add(i);
        }
        return quizQuestions;
    }

    public String getAnswerForSpecificQuestion(Lesson lesson, String questionTitle){
        return lesson.getQuiz().getQuestion(questionTitle).correctAnswer;
    }

    public int getNumberOfQuizzes(Lesson lesson){
        return lesson.getQuiz().questions.length;
    }
    
    public int getMaxQuizScore(Lesson lesson){
        return getQuizQuestions(lesson).size();
    }

    public boolean checkUserAnswer(Lesson lesson, String questionTitle, String answer){
        return getAnswerForSpecificQuestion(lesson, questionTitle).equals(answer);
    }
}

