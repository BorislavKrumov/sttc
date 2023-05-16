package com.darkstyler.sttc.config;

import com.darkstyler.sttc.model.entity.User;
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

import static com.darkstyler.sttc.common.constant.ApplicationConstant.ADMIN;
import static com.darkstyler.sttc.common.constant.ApplicationConstant.COURSE_API;
import static com.darkstyler.sttc.common.constant.ApplicationConstant.LOGIN_API;
import static com.darkstyler.sttc.common.constant.ApplicationConstant.MANAGE_USER_API;
import static com.darkstyler.sttc.common.constant.ApplicationConstant.QUESTION_API;
import static com.darkstyler.sttc.common.constant.ApplicationConstant.QUIZ_API;
import static com.darkstyler.sttc.common.constant.ApplicationConstant.QUIZ_RESULT_API;
import static com.darkstyler.sttc.common.constant.ApplicationConstant.REGISTER_API;
import static com.darkstyler.sttc.common.constant.ApplicationConstant.TEACHER;
import static com.darkstyler.sttc.common.constant.ApplicationConstant.USER;


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
				.antMatchers(REGISTER_API).permitAll()
				.antMatchers(LOGIN_API).permitAll()

				.antMatchers(HttpMethod.POST, COURSE_API).hasAuthority(TEACHER)
				.antMatchers(HttpMethod.GET, COURSE_API).hasAnyAuthority(USER, TEACHER)
				.antMatchers(HttpMethod.PUT, COURSE_API).hasAuthority(TEACHER)
				.antMatchers(HttpMethod.DELETE, COURSE_API).hasAuthority(TEACHER)

				.antMatchers(HttpMethod.POST, QUIZ_API).hasAuthority(TEACHER)
				.antMatchers(HttpMethod.GET, QUIZ_API).hasAnyAuthority(USER, TEACHER)
				.antMatchers(HttpMethod.PUT, QUIZ_API).hasAuthority(TEACHER)
				.antMatchers(HttpMethod.DELETE, QUIZ_API).hasAuthority(TEACHER)

				.antMatchers(HttpMethod.POST, QUESTION_API).hasAuthority(TEACHER)
				.antMatchers(HttpMethod.GET, QUESTION_API).hasAnyAuthority(USER, TEACHER)
				.antMatchers(HttpMethod.PUT, QUESTION_API).hasAuthority(TEACHER)
				.antMatchers(HttpMethod.DELETE, QUESTION_API).hasAuthority(TEACHER)

				.antMatchers(HttpMethod.POST, QUIZ_RESULT_API).hasAuthority(USER)
				.antMatchers(HttpMethod.GET, QUIZ_RESULT_API).hasAuthority(USER)

				.antMatchers(HttpMethod.GET, MANAGE_USER_API).hasAuthority(ADMIN)
				.antMatchers(HttpMethod.PUT, MANAGE_USER_API).hasAuthority(ADMIN)

				.anyRequest()
				.denyAll()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
