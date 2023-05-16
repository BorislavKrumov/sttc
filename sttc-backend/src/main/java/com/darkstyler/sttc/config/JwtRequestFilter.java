package com.darkstyler.sttc.config;

import com.darkstyler.sttc.common.exception.AuthenticationException;
import com.darkstyler.sttc.service.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		try {
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				try {
					username = jwtUtil.extractUsername(jwtToken);
				} catch (IllegalArgumentException e) {
					throw new AuthenticationException("Unable to get JWT token.");
				} catch (ExpiredJwtException e) {
					throw new AuthenticationException("Session expired.");
				}
				UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
				if (jwtUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				} else {
					throw new AuthenticationException("Authentication error, please try again.");
				}
				if (!userDetails.isEnabled()) {
					throw new AuthenticationException("This account is disabled.");
				}
			}
			filterChain.doFilter(request, response);
		} catch (AuthenticationException e) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.getWriter().write(convertObjectToJson(e.getMessage()));
		}
	}

	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
