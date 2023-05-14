package com.darkstyler.sttc.controller;

import com.darkstyler.sttc.model.dto.LoginRequest;
import com.darkstyler.sttc.model.dto.LoginResponse;
import com.darkstyler.sttc.model.dto.UserDto;
import com.darkstyler.sttc.model.entity.User;
import com.darkstyler.sttc.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	private final ModelMapper modelMapper;

	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@Valid @RequestBody UserDto userDto) {
		authService.registerUserService(modelMapper.map(userDto, User.class));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = authService.loginUserService(loginRequest);
		return new ResponseEntity<>(loginResponse, HttpStatus.ACCEPTED);
	}


}
