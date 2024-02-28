package com.governance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.governance.entity.Application;
import com.governance.entity.ApplicationStatus;
import com.governance.entity.User;
import com.governance.exception.ApplicationNotFoundException;
import com.governance.exception.NotFoundException;
import com.governance.repository.ApplicationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private UserService userService; // Assuming a UserService for user-related operations

	public List<Application> getAllApplications() {
		return applicationRepository.findAll();

	}

	public List<Application> getApplicationsByUserId(Long Id) {
		try {
			List<Application> applications = applicationRepository.findByUserId(Id);
			if (applications.isEmpty()) {
				throw new NotFoundException("No applications found for user with ID: " + Id);
			} else {
				return applications;
			}
		} catch (Exception e) {

			throw new RuntimeException("Internal Server Error");
		}
	}

	public String createApplication(Long Id, String description) {
		Optional<User> optionalApplication = userService.getUserById(Id);
		if (optionalApplication.isPresent()) {

			Application application = new Application();
			User user = userService.getUserById(Id).orElseThrow(() -> new RuntimeException("User not found"));
			application.setUser(user);
			application.setDescription(description);
			application.setSubmissionTime(LocalDateTime.now());
			application.setStatus(ApplicationStatus.PENDING);

			applicationRepository.save(application);
			return "Application Created";
		} else {
			return "Citizen Id Not found";
		}
	}

	public Application updateApplicationStatus(Long applicationId, ApplicationStatus status) {
		Application application = getApplicationById(applicationId);
		application.setStatus(status);
		return applicationRepository.save(application);
	}

	public Application getApplicationById(Long applicationId) {
		return applicationRepository.findById(applicationId)
				.orElseThrow(() -> new ApplicationNotFoundException("Application not found with id: " + applicationId));
	}

	public void deleteApplication(Long applicationId) {
		applicationRepository.findById(applicationId)
				.orElseThrow(() -> new ApplicationNotFoundException("Application not found with id: " + applicationId));
	}
}
