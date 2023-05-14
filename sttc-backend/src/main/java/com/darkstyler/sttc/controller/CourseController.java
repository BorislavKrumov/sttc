package com.darkstyler.sttc.controller;

import com.darkstyler.sttc.model.dto.CourseDto;
import com.darkstyler.sttc.model.dto.CourseRequestDto;
import com.darkstyler.sttc.model.dto.CourseResponseDto;
import com.darkstyler.sttc.model.entity.Course;
import com.darkstyler.sttc.service.CourseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
public class CourseController {

	private final CourseService courseService;

	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<Void> addCourse(@Valid @RequestBody CourseDto courseDto) {
		courseService.addCourse(modelMapper.map(courseDto, Course.class));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CourseResponseDto>> getCourses() {
		List<Course> listCourse = courseService.getCourses();
		List<CourseResponseDto> courseDtoList = listCourse.stream().map(course -> modelMapper.map(course, CourseResponseDto.class)).collect(Collectors.toList());
		return new ResponseEntity<>(courseDtoList, HttpStatus.OK);
	}

	@GetMapping("/{courseId}")
	public ResponseEntity<CourseDto> getCourse(@PathVariable Long courseId) {
		Course course = courseService.getCourse(courseId);
		return new ResponseEntity<>(modelMapper.map(course,CourseDto.class),HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<CourseResponseDto> updateCourse(@Valid @RequestBody CourseRequestDto courseDto) {
		Course course = courseService.updateCourse(modelMapper.map(courseDto,Course.class));
		return new ResponseEntity<>(modelMapper.map(course,CourseResponseDto.class),HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteCourse(@RequestParam Long courseId) {
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
