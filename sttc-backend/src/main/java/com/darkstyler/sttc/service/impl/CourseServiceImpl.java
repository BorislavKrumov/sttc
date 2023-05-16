package com.darkstyler.sttc.service.impl;

import com.darkstyler.sttc.common.exception.CourseException;
import com.darkstyler.sttc.model.entity.Course;
import com.darkstyler.sttc.repository.CourseRepository;
import com.darkstyler.sttc.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	@Override
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourse(Long id) {
		return courseRepository.findById(id).orElseThrow(() -> new CourseException("Course not found!"));
	}

	@Override
	public Course updateCourse(Course course) {
		courseRepository.findById(course.getId()).orElseThrow(() -> new CourseException("Course does not exists!"));
		return courseRepository.save(course);
	}

	@Override
	public void deleteCourse(Long id) {
		courseRepository.delete(getCourse(id));
	}
}
