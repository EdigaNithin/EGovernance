package com.example.eGovernance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.governance.DTO.UserDTO;
import com.governance.controller.UserController;
import com.governance.entity.User;
import com.governance.exception.CustomException;
import com.governance.exception.PasswordMissmatchException;
import com.governance.exception.UserNotFound;
import com.governance.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Test
	public void testCreateUser() throws CustomException {
		// Mocking input data
		UserDTO userDTO = new UserDTO("NithinEdiga", "NithinEdiga@example.com", "Nithin123@");
		userDTO.setEmail("NithinEdiga@example.com");
		userDTO.setPassword("Nithin123@");

		// Mocking the service response
		User createdUser = new User();
		createdUser.setId(1L);
		createdUser.setEmail("NithinEdiga@example.com");

		when(userService.createUser(any())).thenReturn(createdUser);

		// Mocking BindingResult
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		// Perform the test
		ResponseEntity<Object> response = userController.createUser(userDTO, bindingResult);

		// Assertions
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(createdUser, response.getBody());
	}

	@Test
	public void testLoginUser() throws UserNotFound, PasswordMissmatchException {
		// Mocking input data
		String email = "NithinEdiga@example.com";
		String password = "Nithin123@";

		// Mocking the service response
		User loggedUser = new User();
		loggedUser.setId(1L);
		loggedUser.setEmail(email);

		when(userService.loginUser(anyString(), anyString())).thenReturn(loggedUser);

		// Perform the test
		ResponseEntity<User> response = userController.loginUser(email, password);

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(loggedUser, response.getBody());
	}
}
