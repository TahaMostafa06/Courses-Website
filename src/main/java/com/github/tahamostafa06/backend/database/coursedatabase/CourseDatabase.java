package com.github.tahamostafa06.backend.database.coursedatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.tahamostafa06.backend.database.common.JsonDatabase;
import com.google.gson.reflect.TypeToken;

public class CourseDatabase extends JsonDatabase<Course> {

	public CourseDatabase() throws IOException {
		super("Courses.json", new TypeToken<Map<String, Course>>(){});
	}

	public Course addCourse(String instructorId, String title, String description) {
		var course = new Course(instructorId, title, description);
		var courseId = generateNewId(course);
		records.put(courseId, course);
		return course;
	}

	public void removeCourse(Course course) {
		for (var courseEntry : records.entrySet()) {
			if (courseEntry.getValue() == course)
				records.remove(courseEntry.getKey());
		}
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

}


