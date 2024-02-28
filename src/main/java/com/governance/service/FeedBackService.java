package com.governance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.governance.DTO.FeedBackDto;
import com.governance.entity.FeedBack;
import com.governance.repository.FeedBackRepository;

@Service
public class FeedBackService {
	@Autowired
	FeedBackRepository repo;

	public FeedBack saveReview(FeedBackDto feedbackDto) {
		// Create a new FeedBack entity
		FeedBack feedback = new FeedBack();

		// Map properties from FeedBackDto to FeedBack entity
		feedback.setRating(feedbackDto.getRating());
		feedback.setFeedback(feedbackDto.getFeedback());

		// Save the mapped entity
		return repo.save(feedback);
	}

//	public void deleteReview(String id){
//		repo.deleteById(id);
//	}
	public List<FeedBack> getAllReviews11() {
		return (List<FeedBack>) repo.findAll();
	}

}