package com.governance.controller;

import com.governance.entity.Application;
import com.governance.entity.ApplicationStatus;
import com.governance.exception.BadRequestException;
import com.governance.exception.NotFoundException;
import com.governance.service.ApplicationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	private ApplicationService applicationService;

	@GetMapping
	public ResponseEntity<List<Application>> getAllApplications() throws NotFoundException{
		logger.info("Fetching all applications");
		List<Application> applications = applicationService.getAllApplications();
		return new ResponseEntity<>(applications, HttpStatus.OK);
	}

	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Application>> getApplicationsByUserId(@PathVariable Long userId)throws NotFoundException {
		logger.info("Fetching applications for user with ID: {}", userId);
		List<Application> applications = applicationService.getApplicationsByUserId(userId);
		return new ResponseEntity<>(applications, HttpStatus.OK);
	}

	
	@PostMapping("/user/{userId}")
	public ResponseEntity<Application> createApplication(@PathVariable Long userId,
			@RequestBody Map<String, String> requestBody)throws NotFoundException {
		logger.info("Creating application for user with ID: {}", userId);

		// Validate description
		String description = requestBody.get("description");
		if (description == null || description.isEmpty()) {
			logger.error("Description cannot be empty");
			throw new BadRequestException("Description cannot be empty");
		}

		applicationService.createApplication(userId, description);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	
	@PostMapping("/{applicationId}")
	public ResponseEntity<Application> updateApplicationStatus(@PathVariable Long applicationId,
			@RequestParam String status)throws NotFoundException {
		logger.info("Updating status for application with ID: {} to {}", applicationId, status);

		ApplicationStatus applicationStatus = ApplicationStatus.valueOf(status.toUpperCase());
		Application updatedApplication = applicationService.updateApplicationStatus(applicationId, applicationStatus);
		return new ResponseEntity<>(updatedApplication, HttpStatus.OK);
	}

	
	@DeleteMapping("/{applicationId}") 
	public ResponseEntity<String> deleteApplication(@PathVariable Long applicationId)throws NotFoundException {
		logger.info("Deleting application with ID: {}", applicationId);

		applicationService.deleteApplication(applicationId);
		return new ResponseEntity<>("Application deleted successfully", HttpStatus.OK);
	}
}
