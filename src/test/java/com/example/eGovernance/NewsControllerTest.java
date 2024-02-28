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

import com.governance.controller.NewsController;
import com.governance.entity.News;
import com.governance.exception.NewsNotFoundException;
import com.governance.service.NewsService;

@ExtendWith(MockitoExtension.class)
public class NewsControllerTest {

	@Mock
	private NewsService newsService;

	@InjectMocks
	private NewsController newsController;

	@Test
	public void testGetAllNews() {
		// Mocking the service response
		List<News> mockNewsList = Arrays.asList(new News(), new News());
		when(newsService.getAllNews()).thenReturn(mockNewsList);

		// Perform the test
		ResponseEntity<List<News>> response = newsController.getAllNews();

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockNewsList, response.getBody());
	}

	@Test
	public void testGetNewsById() throws NewsNotFoundException {
		// Mocking input data
		Long newsId = 1L;

		// Mocking the service response
		Optional<News> mockNews = Optional.of(new News());
		when(newsService.getNewsById(eq(newsId))).thenReturn(mockNews);

		// Perform the test
		ResponseEntity<News> response = newsController.getNewsById(newsId);

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
