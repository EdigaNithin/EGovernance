package com.governance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(value = CustomAdminException.class)
	public ResponseEntity<Object> customAdminException() {
		return new ResponseEntity<>("Admin Already Exists!!!!", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = AdminNotFoundException.class)
	public ResponseEntity<Object> adminNotFoundException() {
		return new ResponseEntity<>("Admin Not Found!!!!", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PasswordMissmatchException.class)
	public ResponseEntity<Object> passwordNotMatch() {
		return new ResponseEntity<>("Please Enter Correct Password...", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Object> exception() {
		return new ResponseEntity<>("Person Already Exists!!!!", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = UserNotFound.class)
	public ResponseEntity<Object> userNotFoundException() {
		return new ResponseEntity<>("Person Not Found!!!!", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> NotFoundException() {
		return new ResponseEntity<>("Application is not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = JobNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleJobNotFoundException(JobNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job Not Found");
	}

	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: " + ex.getMessage());
	}

	@ExceptionHandler(value = InternalServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleInternalServerErrorException(InternalServerErrorException ex) {
		String errorMessage = (ex.getMessage() != null) ? ex.getMessage() : "Error Occurred While Creating News";
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = ResourceNotFoundFeedBackException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundFeedBackException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = BadRequestFeedBackException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleBadRequestException(BadRequestFeedBackException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleException(Exception ex) {
		return new ResponseEntity<>("Internal Server Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = NewsNotFoundException.class)
	public ResponseEntity<Object> NewsNotFoundException() {
		return new ResponseEntity<>("News is not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CitizenNotFoundException.class)
	public ResponseEntity<Object> CitizenNotFoundException() {
		return new ResponseEntity<>("Citizen not found", HttpStatus.NOT_FOUND);
	}

}
