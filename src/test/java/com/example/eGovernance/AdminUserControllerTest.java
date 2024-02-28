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

import com.governance.DTO.AdminDTO;
import com.governance.controller.AdminUserController;
import com.governance.entity.Admin;
import com.governance.exception.AdminNotFoundException;
import com.governance.exception.CustomAdminException;
import com.governance.exception.PasswordMissmatchException;
import com.governance.service.AdminService;

@ExtendWith(MockitoExtension.class)
public class AdminUserControllerTest {

	@Mock
	private AdminService adminService;

	@InjectMocks
	private AdminUserController adminController;

	@Test
	public void testCreateAdmin() throws CustomAdminException {
		// Mocking input data
		AdminDTO adminDTO = new AdminDTO("nithin", "nithin12@gmail.com", "Nithin12@");
		adminDTO.setEmail("nithin12@gmail.com");
		adminDTO.setPassword("Nithin12@");

		// Mocking the service response
		Admin createdAdmin = new Admin();
		createdAdmin.setId(1L);
		createdAdmin.setEmail("nithin12@gmail.com");

		when(adminService.createAdmin(any())).thenReturn(createdAdmin);

		// Mocking BindingResult
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		// Perform the test
		ResponseEntity<Object> response = adminController.createAdmin(adminDTO, bindingResult);

		// Assertions
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(createdAdmin, response.getBody());
	}

	@Test
	public void testLoginAdmin() throws AdminNotFoundException, PasswordMissmatchException {
		// Mocking input data
		String email = "nithin12@gmail.com";
		String password = "Nithin12@";

		// Mocking the service response
		Admin loggedAdmin = new Admin();
		loggedAdmin.setId(1L);
		loggedAdmin.setEmail(email);

		when(adminService.loginAdmin(anyString(), anyString())).thenReturn(loggedAdmin);

		// Perform the test
		ResponseEntity<Admin> response = adminController.loginAdmin(email, password);

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(loggedAdmin, response.getBody());
	}
}
