package com.github.tahamostafa06.backend.database.coursedatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.github.tahamostafa06.backend.database.common.JsonDatabase;
import com.google.gson.reflect.TypeToken;

public class CourseDatabase extends JsonDatabase<Course> {

	public CourseDatabase() throws IOException {
		super("Courses.json", new TypeToken<Map<String, Course>>(){});
	}

	// get progress of course for student
	public Collection<ArrayList<String>> getStudentsProgress(String courseID) {
		var course = this.records.get(courseID);
		var studentsProgress = course.getStudentsAndLessonsDone().values();
		return studentsProgress;
	}

	// get Course Instructor
	public String getCourseInstructor(String courseID) {
		var course = this.records.get(courseID);
		var courseInstructor = course.getInstructorId();
		return courseInstructor;
	}

	@Override
	public String generateNewId(Course course) {
		String idPrefix = "C-";
		var generator = new Random();
		String id;
		var keys = this.records.keySet();
		while (true) {
			id = idPrefix + generator.nextLong(1000000, 9999999);
			if (!keys.contains(id))
				return id;
		}
	}

	public List<Course> getAllCourses() {
		return List.copyOf(this.records.values());
	}

	public ArrayList<String> getLessons(String courseID){
		ArrayList<String> lessons = new ArrayList<>();
		var course = this.records.get(courseID);
		var temp = List.copyOf(course.getLessons().keySet());
		lessons.addAll(temp);
		return lessons;
	}

	public void completeLesson(Lesson lessonToComplete, String studentID, String courseID) {
		var course = this.records.get(courseID);
		ArrayList<String> allLessons = getLessons(courseID);
		for (var lesson : allLessons){
			if (lesson.equals(lessonToComplete)){
				var studentProgress = course.getStudentsAndLessonsDone().get(studentID);
				studentProgress.add(lesson);
			}
		}
    }

}


