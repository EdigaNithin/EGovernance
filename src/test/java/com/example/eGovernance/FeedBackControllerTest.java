package com.example.eGovernance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.governance.controller.FeedBackController;
import com.governance.entity.FeedBack;
import com.governance.exception.CustomException;
import com.governance.service.FeedBackService;

@ExtendWith(MockitoExtension.class)
public class FeedBackControllerTest {

	@Mock
	private FeedBackService feedBackService;

	@InjectMocks
	private FeedBackController feedBackController;

	@Test
	public void testGetAllReviews() throws CustomException {
		// Mocking the service response
		List<FeedBack> mockReviews = Arrays.asList(new FeedBack(), new FeedBack());
		when(feedBackService.getAllReviews11()).thenReturn(mockReviews);

		// Perform the test
		ResponseEntity<List<FeedBack>> response = feedBackController.getAllReviews();

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockReviews, response.getBody());
	}

	@Test
	public void testGetAllReviewsNoContent() throws CustomException {
		// Mocking the service response
		when(feedBackService.getAllReviews11()).thenReturn(Collections.emptyList());

		// Perform the test
		ResponseEntity<List<FeedBack>> response = feedBackController.getAllReviews();

		// Assertions
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertNull(response.getBody());
	}

}
