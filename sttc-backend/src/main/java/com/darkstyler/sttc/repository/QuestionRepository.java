package com.darkstyler.sttc.repository;

import com.darkstyler.sttc.model.entity.Question;
import com.darkstyler.sttc.model.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	List<Question> findByQuiz(Quiz quiz);

}
