package com.darkstyler.sttc.model.dto;

import com.darkstyler.sttc.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class UserDto {

	private long userId;

	@NotEmpty(message = "This field cannot be empty. Please, provide your first name!")
	@Size(max = 255, message = "Invalid length. Please,enter valid first name!")
	private String firstName;

	@NotEmpty(message = "This field cannot be empty. Please, provide your last name!")
	@Size(max = 255, message = "Invalid length. Please,enter valid last name!")
	private String lastName;

	@NotEmpty(message = "This field cannot be empty. Please, provide username!")
	@Size(max = 255, message = "Invalid length. Please,enter valid username!")
	private String username;

	@NotEmpty(message = "This field cannot be empty. Please, provide password!")
	@Size(min = 6, max = 255, message = "Please provide password between 6 and 255 chars!")
	private String password;

	@NotEmpty(message = "This field cannot be empty. Please, provide email!")
	@Size(max = 255, message = "Invalid length. Please,enter valid email!")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email address!")
	private String email;

	private Set<Role> roles;

	private boolean isActive;

}
