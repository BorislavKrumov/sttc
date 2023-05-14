package com.darkstyler.sttc.controller;

import com.darkstyler.sttc.model.entity.Question;
import com.darkstyler.sttc.model.entity.Quiz;
import com.darkstyler.sttc.service.QuestionService;
import com.darkstyler.sttc.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<?> addQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.addQuestion(question));
	}

	@GetMapping("/")
	public ResponseEntity<?> getQuestions() {
		return ResponseEntity.ok(questionService.getQuestions());
	}

	@GetMapping("/{questionId}")
	public ResponseEntity<?> getQuestion(@PathVariable Long questionId) {
		return ResponseEntity.ok(questionService.getQuestion(questionId));
	}

	@GetMapping(value = "/", params = "quizId")
	public ResponseEntity<?> getQuestionsByQuiz(@RequestParam Long quizId) {
		Quiz quiz = quizService.getQuiz(quizId);
		List<Question> questions = quiz.getQuestions();
		return ResponseEntity.ok(questions);
	}

	@PutMapping("/{questionId}")
	public ResponseEntity<?> updateQuestion(@PathVariable Long questionId, @RequestBody Question question) {
		if (questionService.getQuestion(questionId) != null) {
			return ResponseEntity.ok(questionService.updateQuestion(question));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question with id : " + String.valueOf(questionId) + ", doesn't exists");
	}

	@DeleteMapping("/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
		questionService.deleteQuestion(questionId);
		return ResponseEntity.ok(true);
	}
}
