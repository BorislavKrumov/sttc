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
public class UserUpdateDto {
	private long userId;

	@NotEmpty(message = "This field cannot be empty. Please, provide email!")
	@Size(max = 255, message = "Invalid length. Please,enter valid email!")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email address!")
	private String email;

	private Set<Role> roles;

	private boolean isActive;
}
