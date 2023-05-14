package com.darkstyler.sttc.repository;

import com.darkstyler.sttc.model.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
	List<QuizResult> findByUserId(Long userId);

}
