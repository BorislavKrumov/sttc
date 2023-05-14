package com.darkstyler.sttc.service;

import com.darkstyler.sttc.model.dto.LoginRequest;
import com.darkstyler.sttc.model.dto.LoginResponse;
import com.darkstyler.sttc.model.entity.User;

public interface AuthService {

	User registerUserService(User user);

	LoginResponse loginUserService(LoginRequest loginRequest);

}
