package com.darkstyler.sttc.model.dto;

import com.darkstyler.sttc.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
	private User user;
	private String jwtToken;
}

