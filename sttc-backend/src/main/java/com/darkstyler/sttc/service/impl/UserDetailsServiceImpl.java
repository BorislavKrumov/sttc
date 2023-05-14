package com.darkstyler.sttc.service.impl;

import com.darkstyler.sttc.exception.AuthenticationException;
import com.darkstyler.sttc.model.entity.User;
import com.darkstyler.sttc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new AuthenticationException("User Not Found!"));

	}
}
