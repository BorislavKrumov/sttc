package com.darkstyler.sttc.service.impl;

import com.darkstyler.sttc.common.exception.QuizException;
import com.darkstyler.sttc.model.entity.Course;
import com.darkstyler.sttc.model.entity.Quiz;
import com.darkstyler.sttc.repository.QuizRepository;
import com.darkstyler.sttc.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	public QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		if(quizRepository.findByTitle(quiz.getTitle()).isPresent()){
			throw new QuizException("Quiz already exists!");
		}
		return quizRepository.save(quiz);
	}

	@Override
	public List<Quiz> getQuizzes() {
		return quizRepository.findAll();
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		return quizRepository.findById(quizId).isPresent() ? quizRepository.findById(quizId).get() : null;
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	@Override
	public void deleteQuiz(Long quizId) {
		quizRepository.deleteById(quizId);
	}

	@Override
	public List<Quiz> getQuizByCourse(Course course) {
		return quizRepository.findByCourse(course);
	}
}
