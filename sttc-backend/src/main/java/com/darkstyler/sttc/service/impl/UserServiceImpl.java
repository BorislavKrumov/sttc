package com.darkstyler.sttc.service.impl;

import com.darkstyler.sttc.model.entity.User;
import com.darkstyler.sttc.repository.UserRepository;
import com.darkstyler.sttc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
