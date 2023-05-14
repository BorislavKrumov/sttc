package com.darkstyler.sttc.service;

import com.darkstyler.sttc.model.entity.Course;

import java.util.List;

public interface CourseService {
	Course addCourse(Course course);

	List<Course> getCourses();

	Course getCourse(Long id);

	Course updateCourse(Course course);

	void deleteCourse(Long id);

}
