package com.github.tahamostafa06.backend.database.coursedatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.jspecify.annotations.NullMarked;
import com.github.tahamostafa06.backend.Certificate.Certificate;
import com.github.tahamostafa06.backend.Certificate.CertificateDatabase;

import com.github.tahamostafa06.backend.database.common.Record;

public class Course implements Record {
    private String title;
    private String description;
    private String instructorId;
    private String status;
    private HashMap<String, Lesson> lessons;
    private HashMap<String, HashMap<String, StudentLessonProgress>> students;
    private CertificateDatabase certificateDb;
    private Certificate certificate;

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

    @NullMarked
    public Lesson addLesson(String title, String content, Collection<String> optionalResources) {
        ArrayList<Question> quiz = new ArrayList<>();
        var lesson = new Lesson(title, content, optionalResources, quiz);
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

    public void setInstructorId(String instructorId) {
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

    public Map<String, HashMap<String, StudentLessonProgress>> getStudentLessonProgress() {
        return students;
    }

    public List<String> getStudents() {
        return List.copyOf(students.keySet());
    }

    public ArrayList<Question> getQuizQuestions(Lesson lesson) {
        return lesson.quiz;
    }

    public String getAnswerForSpecificQuestion(Lesson lesson, String questionTitle) {
        var questions = getQuizQuestions(lesson);
        for (var q : questions) {
            if (q.getQuestion().equals(questionTitle))
                return q.getCorrectAnswer();
        }
        return "";
    }

    public int getMaxQuizScore(Lesson lesson) {
        return getQuizQuestions(lesson).size();
    }

    public boolean checkUserAnswer(Lesson lesson, String questionTitle, String answer) {
        return getAnswerForSpecificQuestion(lesson, questionTitle).equals(answer);
    }

    public boolean getQuizStatusForStudent(String lessonId, String studentID) {
        return students.get(studentID).get(lessonId).isPassed();
    }

    public ArrayList<ArrayList<Integer>> getStudentAttemptScores(String lessonID, String studentID) {
        return students.get(studentID).get(lessonID).getAttemptsScores();
    }

    public ArrayList<ArrayList<Integer>> getStudentAttemptScore(String lessonID, String studentID) {
        return students.get(studentID).get(lessonID).getAttemptsScores();
    }

    public ArrayList<ArrayList<String>> getStudentAttemptQuestions(String lessonID, String studentID) {
        return students.get(studentID).get(lessonID).getAttemptsQuestions();
    }

    public ArrayList<ArrayList<String>> getStudentAttemptAnswers(String lessonID, String studentID) {
        return students.get(studentID).get(lessonID).getAttemptsAnswers();
    }

    public boolean areAllLessonsPassed(String studentID) {
        for (var lessonID : getLessons().keySet()) { 
            var temp=students.get(studentID).get(lessonID).isPassed();
            if (!temp) {
                return false;
            }
        }
        return true;
    }

    public Certificate generateCertificate(String studentID,String courseID) {
        if (areAllLessonsPassed(studentID)) {
            if (certificateDb == null) {
                certificateDb = new CertificateDatabase();
            }
            return certificateDb.createCourseCertificate(studentID, courseID);
        } 
        return null;
    }
}
