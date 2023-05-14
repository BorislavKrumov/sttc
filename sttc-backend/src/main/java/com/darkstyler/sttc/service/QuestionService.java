package com.darkstyler.sttc.service;

import com.darkstyler.sttc.model.entity.Question;
import com.darkstyler.sttc.model.entity.Quiz;

import java.util.List;

public interface QuestionService {

	Question addQuestion(Question question);

	List<Question> getQuestions();

	Question getQuestion(Long quesId);

	Question updateQuestion(Question question);

	void deleteQuestion(Long questionId);

	//Extra Methods
	List<Question> getQuestionsByQuiz(Quiz quiz);

}
