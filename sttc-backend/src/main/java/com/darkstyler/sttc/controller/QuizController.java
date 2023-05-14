package com.darkstyler.sttc.controller;

import com.darkstyler.sttc.model.entity.Course;
import com.darkstyler.sttc.model.entity.Quiz;
import com.darkstyler.sttc.service.CourseService;
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

@CrossOrigin
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@Autowired
	private CourseService courseService;

	@PostMapping
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
		Quiz savedQuiz = quizService.addQuiz(quiz);
		return new ResponseEntity<>(savedQuiz,HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> getQuizzes() {
		return ResponseEntity.ok(quizService.getQuizzes());
	}

	@GetMapping("/{quizId}")
	public ResponseEntity<?> getQuiz(@PathVariable Long quizId) {
		return ResponseEntity.ok(quizService.getQuiz(quizId));
	}

	@GetMapping(value = "/", params = "catId")
	public ResponseEntity<?> getQuizByCourse(@RequestParam Long catId) {
		Course course = courseService.getCourse(catId);
		return ResponseEntity.ok(quizService.getQuizByCourse(course));
	}

	@PutMapping("/{quizId}")
	public ResponseEntity<?> updateQuiz(@PathVariable Long quizId, @RequestBody Quiz quiz) {
		if (quizService.getQuiz(quizId) != null) {
			return ResponseEntity.ok(quizService.updateQuiz(quiz));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz with id : " + String.valueOf(quizId) + ", doesn't exists");
	}

	@DeleteMapping("/{quizId}")
	public ResponseEntity<?> deleteQuiz(@PathVariable Long quizId) {
		quizService.deleteQuiz(quizId);
		return ResponseEntity.ok(true);
	}
}
