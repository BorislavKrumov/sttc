package com.darkstyler.sttc.config;

import com.darkstyler.sttc.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	public AuthenticationManager authenticationManagerBean(HttpSecurity httpSecurity, UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl)
				.passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/api/register").permitAll()
				.antMatchers("/api/login").permitAll()

				.antMatchers(HttpMethod.POST, "/api/course/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/course/**").hasAnyAuthority("USER", "ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/course/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/course/**").hasAuthority("ADMIN")

				.antMatchers(HttpMethod.POST, "/api/quiz/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/quiz/**").hasAnyAuthority("USER", "ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/quiz/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/quiz/**").hasAuthority("ADMIN")

				.antMatchers(HttpMethod.POST, "/api/question/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/question/**").hasAnyAuthority("USER", "ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/question/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/question/**").hasAuthority("ADMIN")

				.antMatchers(HttpMethod.POST, "/api/quizResult/**").hasAuthority("USER")
				.antMatchers(HttpMethod.GET, "/api/quizResult/**").hasAuthority("USER")

				.antMatchers(HttpMethod.GET, "/api/manage/users").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/manage/users").hasAuthority("ADMIN")

				.anyRequest()
				.denyAll()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
