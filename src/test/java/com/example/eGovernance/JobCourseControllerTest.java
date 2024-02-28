package com.example.eGovernance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.governance.controller.JobCourseController;
import com.governance.entity.JobCourse;
import com.governance.exception.JobNotFoundException;
import com.governance.service.JobCourseService;

@ExtendWith(MockitoExtension.class)
public class JobCourseControllerTest {

	@Mock
	private JobCourseService jobCourseService;

	@InjectMocks
	private JobCourseController jobCourseController;

	@Test
	public void testGetAllJobCourses() throws Exception {
		// Mocking the service response
		List<JobCourse> mockJobCourseList = Arrays.asList(new JobCourse(), new JobCourse());
		when(jobCourseService.getAllJobCourses()).thenReturn(mockJobCourseList);

		// Perform the test
		ResponseEntity<List<JobCourse>> response = jobCourseController.getAllJobCourses();

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockJobCourseList, response.getBody());
	}

	@Test
	public void testGetJobCourseById() throws JobNotFoundException {
		// Mocking input data
		long jobCourseId = 1L;

		// Mocking the service response
		JobCourse mockJobCourse = new JobCourse();
		when(jobCourseService.getJobCourseById(eq(jobCourseId))).thenReturn(Optional.of(mockJobCourse));

		// Perform the test
		ResponseEntity<JobCourse> response = jobCourseController.getJobCourseById(jobCourseId);

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockJobCourse, response.getBody());
	}

}
