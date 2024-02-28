package com.governance.DTO;

import jakarta.validation.constraints.*;

/**
 * Data Transfer Object (DTO) for FeedBack entity.
 */
public class FeedBackDto {

	@NotBlank(message = "Rating invalid")
	@Min(value = 0, message = "Rating must be at least 0")
	@Max(value = 10, message = "Rating must be at most 10")
	private String rating;

	@NotBlank(message = "Feedback is required")
	private String feedback;

	public FeedBackDto() {
	}

	public FeedBackDto(String rating, String feedback) {
		this.rating = rating;
		this.feedback = feedback;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}
