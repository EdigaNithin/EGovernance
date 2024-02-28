package com.governance.controller;

import com.governance.entity.JobCourse;
import com.governance.exception.BadRequestException;
import com.governance.exception.InternalServerErrorException;
import com.governance.exception.JobNotFoundException;
import com.governance.service.JobCourseService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/job-courses")
public class JobCourseController {

	@Autowired
	private JobCourseService jobCourseService;

	private static final Logger logger = LoggerFactory.getLogger(JobCourseController.class);

	@GetMapping
	public ResponseEntity<List<JobCourse>> getAllJobCourses() throws Exception {
		try {
			List<JobCourse> jobCourseList = jobCourseService.getAllJobCourses();
			logger.info("Successfully retrieved all job courses.");
			return ResponseEntity.ok(jobCourseList);
		} catch (InternalServerErrorException ex) {
			logger.error("Internal server error: {}", ex.getMessage(), ex);
			throw new InternalServerErrorException("Internal server error: " + ex.getMessage());
		} catch (Exception e) {
			logger.error("Error as Bad Request: {}", e.getMessage(), e);
			throw new BadRequestException("Error as Bad Request: " + e.getMessage());
		}
	}

	@GetMapping("/{jobCourseId}")
	public ResponseEntity<JobCourse> getJobCourseById(@PathVariable Long jobCourseId) {
		try {
			logger.info("Attempting to retrieve JobCourse with ID: {}", jobCourseId);

			JobCourse jobCourse = jobCourseService.getJobCourseById(jobCourseId)
					.orElseThrow(() -> new JobNotFoundException("Job with ID " + jobCourseId + " not found"));

			logger.info("Successfully retrieved JobCourse with ID: {}", jobCourseId);
			return ResponseEntity.ok(jobCourse);
		} catch (JobNotFoundException e) {
			logger.error("JobNotFoundException: {}", e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("An unexpected error occurred while processing the request", e);
			throw new RuntimeException("Internal Server Error");
		}
	}

	@GetMapping("/find")
	public ResponseEntity<JobCourse> getJobCourseByTitle(@RequestParam String title) {
		try {
			JobCourse jobCourse = jobCourseService.getJobCourseByTitle(title)
					.orElseThrow(() -> new JobNotFoundException("Job with title " + title + " not found"));

			logger.info("Job with title '{}' found: {}", title, jobCourse);

			return ResponseEntity.ok(jobCourse);
		} catch (JobNotFoundException e) {
			logger.error("Job not found with title '{}'", title);
			throw e;
		} catch (Exception e) {
			logger.error("An error occurred while processing the request", e);
			throw new RuntimeException("Internal server error", e);
		}
	}

	@PostMapping
	public ResponseEntity<JobCourse> createJobCourse(@Valid @RequestBody JobCourse jobCourse) {
		try {
			JobCourse createdJobCourse = jobCourseService.createOrUpdateJobCourse(jobCourse);
			logger.info("JobCourse created successfully: {}", createdJobCourse);
			return ResponseEntity.ok(createdJobCourse);
		} catch (InternalServerErrorException ex) {
			logger.error("Internal Server Error: {}", ex.getMessage(), ex);
			throw new InternalServerErrorException("Internal server Error" + ex.getMessage());
		} catch (JobNotFoundException ex) {
			logger.error("Job Not Found: {}", ex.getMessage(), ex);
			throw new JobNotFoundException("Job Not Found" + ex.getMessage());
		} catch (BadRequestException e) {
			logger.error("Invalid request data", e);
			throw new BadRequestException("Invalid request data");
		}
	}

	@DeleteMapping("/{jobCourseId}")
	public ResponseEntity<String> deleteJobCourse(@PathVariable Long jobCourseId) {
		logger.info("Deleting job course with ID: {}", jobCourseId);

		if (jobCourseService.getJobCourseById(jobCourseId).isPresent()) {
			try {
				jobCourseService.createOrUpdateJobCourse(new JobCourse());
				logger.info("Job Course with ID {} deleted successfully", jobCourseId);
				return ResponseEntity.ok("Job Course deleted successfully");
			} catch (Exception e) {
				logger.error("Error deleting Job Course with ID: {}", jobCourseId, e);
				throw new BadRequestException("Invalid request data");
			}
		} else {
			logger.warn("Job Course with ID {} not found", jobCourseId);
			throw new JobNotFoundException("Job with ID " + jobCourseId + " not found");
		}
	}
}
