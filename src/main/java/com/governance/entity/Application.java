package com.governance.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.persistence.*;

/**
 * Entity class representing a job application submitted by a user.
 */
@Entity
@Table(name = "applications")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String description;

	private LocalDateTime submissionTime;

	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;

	/**
	 * Default constructor required by JPA.
	 */
	public Application() {

	}

	/**
	 * Get the unique identifier for this application.
	 *
	 * @return The application ID.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set the unique identifier for this application.
	 *
	 * @param id The application ID.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Set the user associated with this application.
	 *
	 * @param user The user submitting the application.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Get the description of this application.
	 *
	 * @return The application description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of this application.
	 *
	 * @param description The application description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the submission time of this application.
	 *
	 * @return The submission time.
	 */
	public LocalDateTime getSubmissionTime() {
		return submissionTime;
	}

	/**
	 * Set the submission time of this application.
	 *
	 * @param submissionTime The submission time.
	 */
	public void setSubmissionTime(LocalDateTime submissionTime) {
		this.submissionTime = submissionTime;
	}

	/**
	 * Get the status of this application.
	 *
	 * @return The application status.
	 */
	public ApplicationStatus getStatus() {
		return status;
	}

	/**
	 * Set the status of this application.
	 *
	 * @param status The application status.
	 */
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	/**
	 * Set the user associated with this application using an optional user.
	 *
	 * @param userById Optional user to associate with this application.
	 */
	public void setUser(Optional<User> userById) {
		// Implementation not provided, consider removing this method or providing
		// appropriate logic.
	}
}
