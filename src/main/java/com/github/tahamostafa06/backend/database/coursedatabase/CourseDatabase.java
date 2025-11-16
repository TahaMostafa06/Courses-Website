package com.github.tahamostafa06.backend.database.coursedatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.github.tahamostafa06.backend.database.common.JsonDatabase;
import com.github.tahamostafa06.backend.database.userdatabase.User;
import com.google.gson.reflect.TypeToken;
import com.github.tahamostafa06.backend.api.Instructor;
import com.github.tahamostafa06.backend.api.Student;

public class CourseDatabase extends JsonDatabase<Course> {

	public CourseDatabase() throws IOException {
		super("Courses.json", new TypeToken<Map<String, Course>>(){});
	}

	public Boolean isStudentEnrolledIn(String courseId, String studentId) {
		return getEnrolledStudents(courseId).contains(studentId);
	}

	public void enroll(String courseId, String studentId) {
		// Get lesson -> get student-progress map -> add k:v = studentId : 0 # 0 lessons
		// done
		this.records.get(courseId).getStudentsAndProgress().put(studentId, 0);
	}

	// get students enrolled in a course by their id
	public Set<String> getEnrolledStudents(String courseID) {
		var course = this.records.get(courseID);
		var studentIds = course.getStudentsAndProgress().keySet();
		return studentIds;
	}

	// get progress of course for student
	public Collection<Integer> getStudentsProgress(String courseID) {
		var course = this.records.get(courseID);
		var studentsProgress = course.getStudentsAndProgress().values();
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

	public List<String> getAllCourses() {
		return List.copyOf(this.records.keySet());
	}

	public List<String> getEnrolledCourses(String studentId) {
		// for each course in records, is student enrolled in course? add to set
		var enrolledCourses = new ArrayList<String>();
		for (var courseID : records.keySet()){
			if (isStudentEnrolledIn(courseID,studentId)){
				enrolledCourses.add(courseID);
			}
		}
		return List.copyOf(enrolledCourses);
	}

	public List<String> getAvailableCourses(String studentId) {
		var availableCourses = new ArrayList<String>();
		for (var courseId : records.keySet()) {
			if (!isStudentEnrolledIn(courseId, studentId)) {
				availableCourses.add(courseId);
			}
		}
		return List.copyOf(availableCourses);
	}

}
