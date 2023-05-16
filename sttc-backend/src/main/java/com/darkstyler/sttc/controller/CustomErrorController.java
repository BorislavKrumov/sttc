package com.darkstyler.sttc.controller;

import com.darkstyler.sttc.exception.AuthenticationException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomErrorController implements ErrorController {
	@ExceptionHandler
	public ResponseEntity<AuthenticationException> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
		return ResponseEntity.status(403).body(new AuthenticationException(e.getMessage()));
	}
}
