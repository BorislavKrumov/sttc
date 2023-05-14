package com.darkstyler.sttc.repository;

import com.darkstyler.sttc.model.entity.Course;
import com.darkstyler.sttc.model.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	List<Quiz> findByCourse(Course course);
	Optional<Quiz> findByTitle(String title);
}