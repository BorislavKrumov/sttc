package com.darkstyler.sttc.controller;

import com.darkstyler.sttc.model.entity.User;
import com.darkstyler.sttc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/manage")
@AllArgsConstructor
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> viewAllUsers() {

		List<User> userList = userService.getAllUsers();

		return new ResponseEntity<>(userList, HttpStatus.OK);
	}


}
