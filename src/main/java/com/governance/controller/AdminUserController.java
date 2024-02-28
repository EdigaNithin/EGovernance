package com.governance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.governance.DTO.AdminDTO;
import com.governance.entity.Admin;
import com.governance.exception.AdminNotFoundException;
import com.governance.exception.CustomAdminException;
import com.governance.exception.PasswordMissmatchException;
import com.governance.service.AdminService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(path = "/admin")
public class AdminUserController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	@Autowired
	private AdminService adminService;

	@PostMapping
	public ResponseEntity<Object> createAdmin(@Valid @RequestBody AdminDTO admin, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.warn("Invalid input: {}", bindingResult.getAllErrors());

			return new ResponseEntity<>("Invalid input", HttpStatus.CONFLICT);
		}

		try {
			Admin createdAdmin = adminService.createAdmin(admin);

			logger.info("Admin created successfully: {}", createdAdmin);

			return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
		} catch (CustomAdminException e) {
			logger.error("Error creating admin: {}", e.getMessage(), e);

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<Object> deleteAdmin(@RequestParam long id) throws AdminNotFoundException {
		logger.info("Deleting admin with ID: {}", id);

		adminService.deleteAdmin(id);

		logger.info("Admin deleted successfully with ID: {}", id);

		return new ResponseEntity<>("admin deleted successfully", HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Admin> loginAdmin(@Valid @RequestParam String email, @RequestParam String password)
			throws AdminNotFoundException, PasswordMissmatchException {

		logger.info("Login request for admin with email: {}", email);

		Admin admin = adminService.loginAdmin(email.toLowerCase(), password);

		logger.info("Admin logged in successfully: {}", admin);

		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

}
