package com.darkstyler.sttc.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CourseResponseDto {
	private Long id;

	@NotEmpty(message = "This field cannot be empty. Please, provide title!")
	@Size(max = 255, message = "Invalid length. Please,enter valid username!")
	private String title;


	@NotEmpty(message = "This field cannot be empty. Please, provide description!")
	@Size(max = 255, message = "Invalid length. Please,enter valid description!")
	private String description;
}
