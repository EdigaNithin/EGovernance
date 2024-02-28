package com.governance.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) representing user information for input and output
 * purposes.
 */
public class UserDTO {

	private String name;

	@Email(message = "Invalid Email")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z0-9!@#$%^&+=-_*])(?!.*\\s).{8,}$", message = "Invalid password format")
	private String password;

	/**
	 * Constructs a new UserDTO with the specified name, email, and password.
	 *
	 * @param name     The user's name.
	 * @param email    The user's email address.
	 * @param password The user's password.
	 */
	public UserDTO(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public UserDTO() {

	}

	/**
	 * Retrieves the user's name.
	 *
	 * @return The user's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the user's name.
	 *
	 * @param name The new name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the user's email address.
	 *
	 * @return The user's email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's email address.
	 *
	 * @param email The new email address to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the user's password.
	 *
	 * @return The user's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password.
	 *
	 * @param password The new password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
