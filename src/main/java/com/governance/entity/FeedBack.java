package com.governance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing feedback data stored in the "FeedBack_form" table.
 */
@Entity
@Table(name = "FeedBack_form")
public class FeedBack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String Rating;
	private String Feedback;

	/**
	 * Default constructor required by JPA.
	 */
	public FeedBack() {
	}

	/**
	 * Parameterized constructor to create a new instance of FeedBack.
	 *
	 * @param Rating   The rating provided in the feedback.
	 * @param Feedback The textual feedback content.
	 */
	public FeedBack(String Rating, String Feedback) {
		this.Rating = Rating;
		this.Feedback = Feedback;
	}

	/**
	 * Gets the unique identifier of the feedback.
	 *
	 * @return The feedback ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the feedback.
	 *
	 * @param id The feedback ID.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the rating provided in the feedback.
	 *
	 * @return The feedback rating.
	 */
	public String getRating() {
		return Rating;
	}

	/**
	 * Sets the rating provided in the feedback.
	 *
	 * @param rating The feedback rating.
	 */
	public void setRating(String rating) {
		Rating = rating;
	}

	/**
	 * Gets the textual content of the feedback.
	 *
	 * @return The feedback content.
	 */
	public String getFeedback() {
		return Feedback;
	}

	/**
	 * Sets the textual content of the feedback.
	 *
	 * @param feedback The feedback content.
	 */
	public void setFeedback(String feedback) {
		Feedback = feedback;
	}

	/**
	 * Generates a string representation of the FeedBack object.
	 *
	 * @return A string containing the feedback details.
	 */
	@Override
	public String toString() {
		return "FeedBack = id=" + id + ", Rating=" + Rating + ", Feedback =" + Feedback + " ";
	}
}
