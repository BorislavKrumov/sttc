package com.darkstyler.sttc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quiz_result")
public class QuizResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quizResId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "total_obtained_marks")
	private float totalObtainedMarks;

	@Column(name = "attempt_datetime")
	private String attemptDatetime;

	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;


}
