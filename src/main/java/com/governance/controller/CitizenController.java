package com.governance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.governance.exception.CitizenNotFoundException;
import com.governance.service.ServiceRequestServiceImpl;
import com.governance.service.UserService;

@RestController
@RequestMapping("/citizens")
@Validated
public class CitizenController {

	private static final Logger logger = LoggerFactory.getLogger(CitizenController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ServiceRequestServiceImpl serviceRequestService;


	@PostMapping("/{id}/apply-water-connection")
	public ResponseEntity<String> applyForWaterConnection(@PathVariable Long id) throws CitizenNotFoundException {
		logger.info("Applying for water connection for citizen with ID: {}", id);
		String response = serviceRequestService.applyForWaterConnection(id);
		logger.info("Water connection application result: {}", response);
		return ResponseEntity.ok(response);
	}


	@PostMapping("/{id}/apply-birth-certificate")
	public ResponseEntity<String> applyForBirthCertificate(@PathVariable Long id) throws CitizenNotFoundException {
		logger.info("Applying for birth certificate for citizen with ID: {}", id);
		String response = serviceRequestService.applyForBirthCertificate(id);
		logger.info("Birth certificate application result: {}", response);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/{id}/apply-marriage-certificate")
	public ResponseEntity<String> applyForMarriageCertificate(@PathVariable Long citizenId,
			@RequestParam(defaultValue = "Uploaded") String invitationCardUploaded) throws CitizenNotFoundException {
		logger.info("Applying for marriage certificate for citizen with ID: {}, Invitation Card Uploaded: {}",
				citizenId, invitationCardUploaded);
		String response = serviceRequestService.applyForMarriageCertificate(citizenId, invitationCardUploaded);
		logger.info("Marriage certificate application result: {}", response);
		return ResponseEntity.ok(response);
	}

}
