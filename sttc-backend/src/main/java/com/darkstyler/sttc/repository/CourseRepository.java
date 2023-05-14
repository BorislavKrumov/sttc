package com.darkstyler.sttc.repository;

import com.darkstyler.sttc.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
	Optional<Course> findByTitle(String title);
}
