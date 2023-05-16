package com.darkstyler.sttc.controller;

import com.darkstyler.sttc.model.dto.UserDto;
import com.darkstyler.sttc.model.dto.UserUpdateDto;
import com.darkstyler.sttc.model.entity.User;
import com.darkstyler.sttc.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/manage/users")
@AllArgsConstructor
public class UserController {

	private UserService userService;

	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<UserDto>> viewAllUsers() {

		List<User> userList = userService.getAllUsers();
		List<UserDto> userDtoList = userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

		return new ResponseEntity<>(userDtoList, HttpStatus.OK);
	}


	@PutMapping
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto) {

		User user = userService.updateUser(modelMapper.map(userUpdateDto, User.class));

		return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.ACCEPTED);
	}

}
