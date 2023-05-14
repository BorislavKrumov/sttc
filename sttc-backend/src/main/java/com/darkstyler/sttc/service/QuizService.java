package com.darkstyler.sttc.service;

import com.darkstyler.sttc.model.entity.Course;
import com.darkstyler.sttc.model.entity.Quiz;

import java.util.List;

public interface QuizService {
	Quiz addQuiz(Quiz quiz);

	List<Quiz> getQuizzes();

	Quiz getQuiz(Long quizId);

	Quiz updateQuiz(Quiz quiz);

	void deleteQuiz(Long quizId);

	// Extra methods
	List<Quiz> getQuizByCourse(Course course);
}
