package com.governance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.governance.entity.ServiceRequest;
import com.governance.entity.User;
import com.governance.repository.ServiceRequestRepository;
import com.governance.repository.UserRepository;

import java.util.Optional;

@Service
public class ServiceRequestServiceImpl {

	@Autowired
	private ServiceRequestRepository serviceRequestRepository;

	@Autowired
	private UserRepository userRepository;

	private boolean isAlreadyApplied(Long userId, String serviceName) {
		return serviceRequestRepository.findByUserIdAndServiceName(userId, serviceName).isPresent();

	}

	public String applyForWaterConnection(Long userId) {
		if (isAlreadyApplied(userId, "Water Connection")) {
			return "Water connection application already submitted by the user.";
		}
		return applyForService(userId, "Water Connection");
	}

	public String applyForBirthCertificate(Long userId) {
		if (isAlreadyAppliedBirth(userId, "Birth Certificate")) {
			return "BirthCertificate application already submitted by the user.";
		}
		return applyForService(userId, "Birth Certificate");
	}

	private boolean isAlreadyAppliedBirth(Long userId, String serviceName) {
		return serviceRequestRepository.findByUserIdAndServiceName(userId, serviceName).isPresent();
	}

	public String applyForMarriageCertificate(Long userId, String invitationCardUploaded) {
		Optional<User> optionalUser = userRepository.findById(userId);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			boolean hasApplied = serviceRequestRepository.existsByUserAndServiceName(user, "Marriage Certificate");
			if (hasApplied) {
				return "Application already submitted for Marriage Certificate.";
			}
			ServiceRequest serviceRequest = new ServiceRequest();
			serviceRequest.setUser(user);
			serviceRequest.setServiceName("Marriage Certificate");
			serviceRequest.setApplicationStatus("Open");
			serviceRequest.setInvitationCardUploaded(invitationCardUploaded);
			serviceRequestRepository.save(serviceRequest);

			return "Application accepted. Your Marriage Certificate will be processed within 48 hours.";
		} else {
			return "Citizen not found.";
		}
	}

	private String applyForService(Long userId, String serviceName) {
		Optional<User> optionalCitizen = userRepository.findById(userId);
		if (optionalCitizen.isPresent()) {
			User user = optionalCitizen.get();
			Optional<ServiceRequest> existingRequest = serviceRequestRepository.findByUserAndServiceName(user,
					serviceName);
			if (existingRequest.isPresent()) {
				return "Application already submitted for this service.";
			}
			ServiceRequest serviceRequest = new ServiceRequest();
			serviceRequest.setUser(user);
			serviceRequest.setServiceName(serviceName);
			serviceRequest.setApplicationStatus("Open");
			serviceRequestRepository.save(serviceRequest);

			return "Application accepted.";
		} else {
			return "Citizen not found.";
		}
	}

}
