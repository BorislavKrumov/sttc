package com.darkstyler.sttc.service;

import com.darkstyler.sttc.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
	List<User> getAllUsers();

	User updateUser(User user);

	User updateUserIcon(MultipartFile file,Long id);
}
