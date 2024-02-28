package com.governance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.governance.DTO.FeedBackDto;
import com.governance.entity.FeedBack;
import com.governance.exception.CustomException;
import com.governance.service.FeedBackService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/governance/reviews")
public class FeedBackController {

	private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);

	@Autowired
	private FeedBackService reviewService;

	@GetMapping("/allreview")
	public ResponseEntity<List<FeedBack>> getAllReviews() throws CustomException {
		try {
			List<FeedBack> reviews = reviewService.getAllReviews11();
			if (reviews.isEmpty()) {
				logger.info("No reviews found.");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.info("Retrieved {} reviews.", reviews.size());
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error retrieving reviews", e);
			throw new CustomException("Error retrieving reviews");
		}
	}

	@PostMapping("/save")
	public ResponseEntity<FeedBack> saveReview(@Valid @RequestBody FeedBackDto feedbackDto) throws CustomException {
		try {
			FeedBack savedReview = reviewService.saveReview(feedbackDto);

			logger.info("Saved review with ID: {}", savedReview.getId());
			return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error saving review", e);
			throw new CustomException("Error saving review");
		}
	}

}
