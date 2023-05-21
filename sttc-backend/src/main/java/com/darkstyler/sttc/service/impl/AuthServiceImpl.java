package com.darkstyler.sttc.service.impl;

import com.darkstyler.sttc.config.JwtUtil;
import com.darkstyler.sttc.common.exception.AuthenticationException;
import com.darkstyler.sttc.common.exception.UserException;
import com.darkstyler.sttc.model.dto.LoginRequest;
import com.darkstyler.sttc.model.dto.LoginResponse;
import com.darkstyler.sttc.model.entity.Role;
import com.darkstyler.sttc.model.entity.User;
import com.darkstyler.sttc.repository.RoleRepository;
import com.darkstyler.sttc.repository.UserRepository;
import com.darkstyler.sttc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public User registerUserService(User user) {
		userRepository.findByUsername(user.getUsername()).ifPresent(s -> {
			throw new UserException("User with this username already exists.");
		});
		userRepository.findByEmail(user.getEmail()).ifPresent(s -> {
			throw new UserException("User with this email already exists.");
		});

		Role role = roleRepository.findById("USER").isPresent() ? roleRepository.findById("USER").get() : null;
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		user.setRoles(userRoles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(true);

		return userRepository.save(user);

	}

	public LoginResponse loginUserService(LoginRequest loginRequest) {

		authenticate(loginRequest.getUsername(), loginRequest.getPassword());
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUsername());
		String token = jwtUtil.generateToken(userDetails);
		User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new AuthenticationException("User not found!"));
		return new LoginResponse(user, token);
	}

	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new RuntimeException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new RuntimeException("INVALID_CREDENTIALS", e);
		}
	}
}
