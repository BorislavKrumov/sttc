package com.darkstyler.sttc.service.impl;

import com.darkstyler.sttc.common.exception.UserException;
import com.darkstyler.sttc.model.entity.User;
import com.darkstyler.sttc.repository.UserRepository;
import com.darkstyler.sttc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		User currentUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new UserException("User not found."));
		currentUser.setRoles(user.getRoles());
		currentUser.setActive(user.isActive());
		currentUser.setEmail(user.getEmail());
		userRepository.save(currentUser);
		return currentUser;
	}

	@Override
	public User updateUserIcon(MultipartFile file, Long id) {
		User currentUser = userRepository.findById(id).orElseThrow(() -> new UserException("User not found."));
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
		if(fileName.contains("..")){
			throw new RuntimeException("Image is unsupported");
		}
		try{
			currentUser.setImage(Base64.getEncoder().encode(file.getBytes()));
		}
		catch (IOException e){
			e.printStackTrace();
		}

		userRepository.save(currentUser);
		return currentUser;
	}

}
