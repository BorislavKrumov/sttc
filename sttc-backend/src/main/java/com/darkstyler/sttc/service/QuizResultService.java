package com.darkstyler.sttc.service;

import com.darkstyler.sttc.model.entity.QuizResult;

import java.util.List;

public interface QuizResultService {
	QuizResult addQuizResult(QuizResult quizResult);

	List<QuizResult> getQuizResults();

	List<QuizResult> getQuizResultsByUser(Long userId);
}
