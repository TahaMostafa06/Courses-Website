package com.github.tahamostafa06.backend.courseservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.github.tahamostafa06.backend.auth.AuthenticationManager;
import com.github.tahamostafa06.backend.auth.LoginToken;
import com.github.tahamostafa06.backend.database.coursedatabase.Course;
import com.github.tahamostafa06.backend.database.coursedatabase.CourseDatabase;
import com.github.tahamostafa06.backend.database.coursedatabase.Lesson;
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
        return course.getStudentsAndLessonsDone().keySet();
    }

    private Integer getEnrolledStudentsCount(Course course) {
        return course.getStudentsAndLessonsDone().keySet().size();
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
        course.getStudentsAndLessonsDone().put(token.getUserId(), new ArrayList<String>());
    }

    public List<CourseItem> getAvailableCourses(LoginToken token) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return null;
        var allCourses = courseDb.getAllCourses();
        var availableCourses = new ArrayList<CourseItem>();
        for (var course : allCourses) {
            if (!isStudentEnrolledIn(course, token.getUserId())) {
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
            if (isStudentEnrolledIn(course, token.getUserId())) {
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
        return course.getStudentsAndLessonsDone().get(token.getUserId()).size();
    }

    public List<LessonItem> getLessons(LoginToken token, CourseItem courseItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
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

    public void completeLesson(LoginToken token, CourseItem courseItem, LessonItem lessonItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return;
        var courseRecord = courseItem.getCourse();
        var lessonRecord = lessonItem.getLesson();
        var studentId = token.getUserId();
        var lessonId = getLessonId(courseRecord, lessonRecord);
        var getStudentProgress = courseRecord.getStudentsAndLessonsDone().get(studentId);
        getStudentProgress.add(lessonId);
    }

    public boolean isLessonDone(LoginToken token, CourseItem courseItem, LessonItem lessonItem) {
        if (!this.authenticationManager.authenticate(token, "Student"))
            return false;
        var courseRecord = courseItem.getCourse();
        var lessonRecord = lessonItem.getLesson();
        var studentId = token.getUserId();
        var getStudentProgress = courseRecord.getStudentsAndLessonsDone().get(studentId);
        return getStudentProgress.contains(getLessonId(courseRecord, lessonRecord));
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
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
            return;
        courseRecord.setDescription(newDescription);
    }

    public void setCourseTitle(LoginToken token, CourseItem courseItem, String newTitle) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
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
        for (var student : courseRecord.getStudentsAndLessonsDone().keySet()) {
            var studentName = userService.getUsernameById(student);
            myStudentsNames.add(studentName);
        }
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
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return null;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
            return null;
        var lesson = courseRecord.addLesson(title, content, additionalResources);

        return new LessonItem(lesson);
    }

    public void removeLesson(LoginToken token, CourseItem courseItem, LessonItem lessonItem) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return ;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
            return ;
        var lessonRecord = lessonItem.getLesson();
        courseRecord.removeLesson(lessonRecord);
    }

    public void setLessonTitle(LoginToken token, CourseItem courseItem, LessonItem lessonItem, String title) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return ;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
            return ;
        var lessonRecord = lessonItem.getLesson();
        lessonRecord.setTitle(title);
    }

    public void setLessonContent(LoginToken token, CourseItem courseItem, LessonItem lessonItem, String content) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return ;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
            return ;
        var lessonRecord = lessonItem.getLesson();
        lessonRecord.setContent(content);
    }

    public void setLessonAdditionalResources(LoginToken token, CourseItem courseItem, LessonItem lessonItem,
            Collection<String> additionalResources) {
        if (!this.authenticationManager.authenticate(token, "Instructor"))
            return ;
        var courseRecord = courseItem.getCourse();
        if (!courseRecord.getInstructorId().equals(token.getUserId()))
            return ;
        var lessonRecord = lessonItem.getLesson();
        lessonRecord.setOptionalResources(new ArrayList<String>(additionalResources));

    }
    
    //Admin API Methods
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
    
    public CourseItem createCourse(LoginToken token, String title, String description, String instructor, String Status) {
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return null;
        var course = courseDb.addCourse(instructor, title, description, Status);
        return new CourseItem(course);
    }
    
    public void setCourseInstructor(LoginToken token, CourseItem courseItem, String instructorId){
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return;
        var courseRecord = courseItem.getCourse();
        courseRecord.setInstructorId(instructorId);
    }
    
    public void setCourseStatus(LoginToken token, CourseItem courseItem, String instructorId){
        if (!this.authenticationManager.authenticate(token, "Admin"))
            return;
        var courseRecord = courseItem.getCourse();
        courseRecord.setStatus(instructorId);
    }
}   
