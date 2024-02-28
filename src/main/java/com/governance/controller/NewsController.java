package com.governance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.governance.DTO.NewsDto;
import com.governance.entity.News;
import com.governance.exception.NewsNotFoundException;
import com.governance.service.NewsService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
public class NewsController {

	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private NewsService newsService;

	@GetMapping
	public ResponseEntity<List<News>> getAllNews() {
		logger.info("Fetching all news");
		List<News> newsList = newsService.getAllNews();
		return ResponseEntity.ok(newsList);
	}

	@GetMapping("/{newsId}")
	public ResponseEntity<News> getNewsById(@PathVariable Long newsId) throws NewsNotFoundException {
		logger.info("Fetching news by ID: {}", newsId);
		Optional<News> news = newsService.getNewsById(newsId);
		return news.map(ResponseEntity::ok).orElseThrow(() -> new NewsNotFoundException("News not found"));
	}

	@GetMapping("/find")
	public ResponseEntity<News> getNewsByTitle(@RequestParam String title) throws NewsNotFoundException {
		logger.info("Fetching news by title: {}", title);
		Optional<News> news = newsService.getNewsByTitle(title);
		return news.map(ResponseEntity::ok).orElseThrow(() -> new NewsNotFoundException("News not found"));
	}

	@PostMapping("/createNews")
	public ResponseEntity<Object> createNews(@Valid @RequestBody NewsDto newsDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.warn("Invalid input during news creation");
			return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
		}

		try {
			News createdNews = newsService.createNews(newsDto);
			logger.info("News created successfully: {}", createdNews);
			return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
		} catch (Exception e) {
			// Log internal server error
			logger.error("Error creating admin: {}", e.getMessage(), e);

			// Return an internal server error response
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{newsId}")
	public ResponseEntity<String> deleteNews(@PathVariable Long newsId) {
		logger.info("Deleting news with ID: {}", newsId);
		try {
			boolean deleted = newsService.deleteNews(newsId);
			if (deleted) {
				return ResponseEntity.ok("News deleted successfully");
			} else {
				// Handle the case where the news with the given ID is not found
				throw new NewsNotFoundException("News not found ");
			}
		} catch (NewsNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Unexpected error occurred: " + e.getMessage());
		}
	}

}
