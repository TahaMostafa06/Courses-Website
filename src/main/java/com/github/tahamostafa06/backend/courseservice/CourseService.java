package com.github.tahamostafa06.backend.courseservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.github.tahamostafa06.backend.Certificate.Certificate;
import com.github.tahamostafa06.backend.auth.AuthenticationManager;
import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.database.coursedatabase.Course;
import com.github.tahamostafa06.backend.database.coursedatabase.CourseDatabase;
import com.github.tahamostafa06.backend.database.coursedatabase.Lesson;
import com.github.tahamostafa06.backend.database.coursedatabase.StudentLessonProgress;
import com.github.tahamostafa06.backend.userservice.UserService;

public class CourseService {
    private CourseDatabase courseDb;
    private UserService userService;
    private AuthenticationManager authenticationManager;

    public CourseService(CourseDatabase courseDb, UserService userService,
            AuthenticationManager authenticationManager) {
        this.courseDb = courseDb;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    // Private methods

    private Set<String> getEnrolledStudents(Course course) {
        return course.getStudentLessonProgress().keySet();
    }

    private Integer getEnrolledStudentsCount(Course course) {
        return getEnrolledStudents(course).size();
    }

    private Boolean isStudentEnrolledIn(Course courseId, String studentId) {
        return getEnrolledStudents(courseId).contains(studentId);
    }

    // Student API methods
    public void enroll(LoginToken token, CourseItem courseItem) {
        var course = courseItem.getCourse();
        if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        if (isStudentEnrolledIn(course, token.getUserId()))
            return;
        course.getStudentLessonProgress().put(token.getUserId(), new HashMap<>());
    }

    public List<CourseItem> getAvailableCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var allCourses = courseDb.getAllCourses();
        var availableCourses = new ArrayList<CourseItem>();
        for (var course : allCourses) {
            if (!isStudentEnrolledIn(course, token.getUserId())
                    && course.getStatus().equals("APPROVED")) {
                availableCourses.add(new CourseItem(course));
            }
        }
        return availableCourses;
    }

    public List<CourseItem> getEnrolledCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var allCourses = courseDb.getAllCourses();
        var enrolledCourses = new ArrayList<CourseItem>();
        for (var course : allCourses) {
            if (isStudentEnrolledIn(course, token.getUserId())
                    && course.getStatus().equals("APPROVED")) {
                enrolledCourses.add(new CourseItem(course));
            }
        }
        return enrolledCourses;
    }

    public Integer getEnrolledStudentCount(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var course = courseItem.getCourse();
        return getEnrolledStudentsCount(course);
    }

    public String getInstructorName(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var course = courseItem.getCourse();
        var instructorId = course.getInstructorId();
        return userService.getUsernameById(instructorId);
    }

    public Integer getLessonCount(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var course = courseItem.getCourse();
        return course.getLessons().size();
    }

    public Integer getFinishedLessonsCount(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var course = courseItem.getCourse();
        var studentProgress = course.getStudentLessonProgress().get(token.getUserId());
        var count = 0;
        for (var lessonProgress : studentProgress.values()) {
            if (lessonProgress.isPassed())
                count += 1;
        }
        return count;
    }

    public List<LessonItem> getLessons(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Student") &&
                !this.authenticationManager.authenticate(token, "Admin"))
            return null;
        var course = courseItem.getCourse();
        var allLessons = new ArrayList<LessonItem>();
        for (var lesson : course.getLessons().values()) {
            allLessons.add(new LessonItem(lesson));
        }
        return allLessons;
    }

    private String getLessonId(Course course, Lesson lesson) {
        for (var lessonEntry : course.getLessons().entrySet()) {
            if (lessonEntry.getValue() == lesson) {
                return lessonEntry.getKey();
            }
        }
        return "";
    }

    public StudentLessonProgress getStudentLessonProgress(LoginToken token, CourseItem courseItem,
            LessonItem lessonItem) {
        return courseItem.getCourse().getStudentLessonProgress().get(token.getUserId())
                .get(getLessonId(courseItem.getCourse(), lessonItem.getLesson()));
    }

    public void submitQuiz(LoginToken token, CourseItem courseItem, LessonItem lessonItem,
            ArrayList<String> answers) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        var courseRecord = courseItem.getCourse();
        var lessonRecord = lessonItem.getLesson();
        var studentId = token.getUserId();
        var lessonId = getLessonId(courseRecord, lessonRecord);
        var questions = new ArrayList<String>();
        var correctAnswers = new ArrayList<String>();
        var scores = new ArrayList<Integer>();
        var quiz = lessonRecord.getQuiz();
        for (var i = 0; i < quiz.size(); i++) {
            var question = quiz.get(i);
            questions.add(question.getQuestion());
            correctAnswers.add(question.getCorrectAnswer());
            if (answers.get(i).equals(question.getCorrectAnswer()))
                scores.add(1);
            else
                scores.add(0);
        }
        var totalScore = 0;
        for (var score : scores) {
            totalScore += score;
        }
        scores.add(totalScore);
        var studentLessonProgress = courseRecord.getStudentLessonProgress().get(studentId);
        if (!studentLessonProgress.containsKey(lessonId))
            studentLessonProgress.put(lessonId, new StudentLessonProgress());
        var lessonProgress = studentLessonProgress.get(lessonId);
        var passed = (totalScore == quiz.size()) || lessonProgress.isPassed();
        lessonProgress.addAnswers(answers);
        lessonProgress.addQuestions(questions);
        lessonProgress.addScores(scores);
        lessonProgress.setPassed(passed);
        // Check if all lessons are done to generate certificate
        boolean generateCertificateCondition = true;
        for (var lessonID : courseRecord.getLessons().keySet()) { 
            if (!studentLessonProgress.get(lessonID).isPassed()) {
                generateCertificateCondition = false;
                break;
            }
        }
        if (generateCertificateCondition)
            courseRecord.generateCertificate(studentId);
    }
    

    public boolean isLessonDone(LoginToken token, CourseItem courseItem, LessonItem lessonItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return false;
        var courseRecord = courseItem.getCourse();
        var lessonRecord = lessonItem.getLesson();
        var studentLessonProgress = courseRecord.getStudentLessonProgress().get(token.getUserId());
        if (!studentLessonProgress.containsKey(getLessonId(courseRecord, lessonRecord)))
            return false;
        else
            return studentLessonProgress.get(getLessonId(courseRecord, lessonRecord)).isPassed();
    }

    // Instructor API Methods
    public List<CourseItem> getMyCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return null;
        var myCourses = new ArrayList<CourseItem>();
        for (var course : courseDb.getAllCourses()) {
            if (course.getInstructorId().equals(token.getUserId()))
                myCourses.add(new CourseItem(course));
        }
        return myCourses;
    }

    public void setCourseDescription(LoginToken token, CourseItem courseItem, String newDescription) {
        boolean isInstructor = this.authenticationManager.authenticate(token, "Instructor");
        boolean isAdmin = this.authenticationManager.authenticate(token, "Admin");
        if (!isInstructor && !isAdmin)
            return;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()) && !isAdmin)
            return;
        courseRecord.setDescription(newDescription);
    }

    public void setCourseTitle(LoginToken token, CourseItem courseItem, String newTitle) {
        boolean isInstructor = this.authenticationManager.authenticate(token, "Instructor");
        boolean isAdmin = this.authenticationManager.authenticate(token, "Admin");
        if (!isInstructor && !isAdmin)
            return;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()) && !isAdmin)
            return;
        courseRecord.setTitle(newTitle);
    }

    public CourseItem createCourse(LoginToken token, String title, String description) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return null;
        var course = courseDb.addCourse(token.getUserId(), title, description);
        return new CourseItem(course);
    }

    public void deleteCourse(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
            return;
        courseDb.removeCourse(courseRecord);
    }

    public List<String> getMyStudentsForCourse(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return null;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
            return null;
        var myStudentsNames = new ArrayList<String>();
        // for (var student : courseRecord.getStudentsAndLessonsDone().keySet()) {
        // var studentName = userService.getUsernameById(student);
        // myStudentsNames.add(studentName);
        // }
        return myStudentsNames;
    }

    public List<LessonItem> getMyLessonsForCourse(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return null;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
            return null;
        var myLessons = new ArrayList<LessonItem>();
        for (var lesson : courseRecord.getLessons().values()) {
            myLessons.add(new LessonItem(lesson));
        }
        return myLessons;
    }

    public LessonItem addLesson(LoginToken token, CourseItem courseItem, String title, String content,
            Collection<String> additionalResources) {
        boolean isInstructor = this.authenticationManager.authenticate(token, "Instructor");
        boolean isAdmin = this.authenticationManager.authenticate(token, "Admin");
        if (!isInstructor && !isAdmin)
            return null;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()) && !isAdmin)
            return null;
        var lesson = courseRecord.addLesson(title, content, additionalResources);
        return new LessonItem(lesson);
    }

    public void removeLesson(LoginToken token, CourseItem courseItem, LessonItem lessonItem) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
            return;
        var lessonRecord = lessonItem.getLesson();
        courseRecord.removeLesson(lessonRecord);
    }

    public void setLessonTitle(LoginToken token, CourseItem courseItem, LessonItem lessonItem, String title) {
        boolean isInstructor = this.authenticationManager.authenticate(token, "Instructor");
        boolean isAdmin = this.authenticationManager.authenticate(token, "Admin");
        if (!isInstructor && !isAdmin)
            return;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()) && !isAdmin)
            return;
        var lessonRecord = lessonItem.getLesson();
        lessonRecord.setTitle(title);
    }

    public void setLessonContent(LoginToken token, CourseItem courseItem, LessonItem lessonItem, String content) {
        boolean isInstructor = this.authenticationManager.authenticate(token, "Instructor");
        boolean isAdmin = this.authenticationManager.authenticate(token, "Admin");
        if (!isInstructor && !isAdmin)
            return;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()) && !isAdmin)
            return;
        var lessonRecord = lessonItem.getLesson();
        lessonRecord.setContent(content);
    }

    public void setLessonAdditionalResources(LoginToken token, CourseItem courseItem, LessonItem lessonItem,
            Collection<String> additionalResources) {
        boolean isInstructor = this.authenticationManager.authenticate(token, "Instructor");
        boolean isAdmin = this.authenticationManager.authenticate(token, "Admin");
        if (!isInstructor && !isAdmin)
            return;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()) && !isAdmin)
            return;
        var lessonRecord = lessonItem.getLesson();
        lessonRecord.setOptionalResources(new ArrayList<>(additionalResources));

    }

    // Admin API Methods
    public List<CourseItem> getAllCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return null;
        var allCourses = new ArrayList<CourseItem>();
        for (var course : courseDb.getAllCourses())
            allCourses.add(new CourseItem(course));
        return allCourses;
    }

    public List<CourseItem> getPendingCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return null;
        var pendingCourses = new ArrayList<CourseItem>();
        for (var course : courseDb.getAllCourses()) {
            if (course.getStatus().equals("PENDING"))
                pendingCourses.add(new CourseItem(course));
        }
        return pendingCourses;
    }

    public CourseItem createCourse(LoginToken token, String title, String description, String instructor,
            String Status) {
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return null;
        var course = courseDb.addCourse(instructor, title, description, Status);
        return new CourseItem(course);
    }

    public void setCourseInstructor(LoginToken token, CourseItem courseItem, String instructorId) {
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return;
        var courseRecord = courseItem.getCourse();
        courseRecord.setInstructorId(instructorId);
    }

    public void setCourseStatus(LoginToken token, CourseItem courseItem, String instructorId) {
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return;
        var courseRecord = courseItem.getCourse();
        courseRecord.setStatus(instructorId);
    }

    public ArrayList<ArrayList<Integer>> getStudentAttemptScores(LoginToken token, CourseItem courseItem,
            String lessonID, String studentID) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return null;
        var courseRecord = courseItem.getCourse();
        return courseRecord.getStudentAttemptScores(lessonID, studentID);
    }

    public ArrayList<ArrayList<String>> getStudentAttemptQuestions(LoginToken token, CourseItem courseItem,
            String lessonID, String studentID) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return null;
        var courseRecord = courseItem.getCourse();
        return courseRecord.getStudentAttemptQuestions(lessonID, studentID);
    }

    public ArrayList<ArrayList<String>> getStudentAttemptAnswers(LoginToken token, CourseItem courseItem,
            String lessonID, String studentID) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return null;
        var courseRecord = courseItem.getCourse();
        return courseRecord.getStudentAttemptAnswers(lessonID, studentID);
    }

    public boolean isStudentEnrolled(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return false;
        var courseRecord = courseItem.getCourse();
        return isStudentEnrolledIn(courseRecord, token.getUserId());
    }

    public boolean areAllLessonsPassed(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return false;
        var courseRecord = courseItem.getCourse();
        return courseRecord.areAllLessonsPassed(token.getUserId());
    }

    public Certificate generateCertificate(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return null;
        var courseRecord = courseItem.getCourse();
        return courseRecord.generateCertificate(token.getUserId());
    }

}
