package com.darkstyler.sttc.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@ToString
@Table(name = "quiz")
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quizId;

	@Column(name = "title")
	private String title;

	@Column(name = "description", length = 5000)
	private String description;

	@Column(name = "max_marks")
	private int maxMarks;

	@Column(name = "number_of_questions")
	private int numberOfQuestions;

	@Column(name = "is_active")
	private boolean iActive = false;

	@ManyToOne(fetch = FetchType.EAGER)
	private Course course;

	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Question> questions = new ArrayList<>();

	@OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<QuizResult> quizResults = new ArrayList<>();
}