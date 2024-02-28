package com.governance.DTO;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) representing a Job Course.
 */
public class JobCourseDTO {

	/** Unique identifier for the job course. */
	private Long jobCourseId;

	/** Title of the job course. */
	@NotBlank
	private String title;

	/** Description of the job course. */
	@NotBlank
	private String description;

	/** Eligibility criteria for the job course. */
	@NotBlank
	private String eligibilityCriteria;

	/** Application deadline for the job course. */
	private Date applicationDeadline;

	/** Status of the job course. */
	@NotBlank
	private String status;

	/**
	 * Get the unique identifier for the job course.
	 *
	 * @return The job course ID.
	 */
	public Long getJobCourseId() {
		return jobCourseId;
	}

	/**
	 * Set the unique identifier for the job course.
	 *
	 * @param jobCourseId The job course ID.
	 */
	public void setJobCourseId(Long jobCourseId) {
		this.jobCourseId = jobCourseId;
	}

	/**
	 * Get the title of the job course.
	 *
	 * @return The job course title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the job course.
	 *
	 * @param title The job course title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the description of the job course.
	 *
	 * @return The job course description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of the job course.
	 *
	 * @param description The job course description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the eligibility criteria for the job course.
	 *
	 * @return The eligibility criteria.
	 */
	public String getEligibilityCriteria() {
		return eligibilityCriteria;
	}

	/**
	 * Set the eligibility criteria for the job course.
	 *
	 * @param eligibilityCriteria The eligibility criteria.
	 */
	public void setEligibilityCriteria(String eligibilityCriteria) {
		this.eligibilityCriteria = eligibilityCriteria;
	}

	/**
	 * Get the application deadline for the job course.
	 *
	 * @return The application deadline.
	 */
	public Date getApplicationDeadline() {
		return applicationDeadline;
	}

	/**
	 * Set the application deadline for the job course.
	 *
	 * @param applicationDeadline The application deadline.
	 */
	public void setApplicationDeadline(Date applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}

	/**
	 * Get the status of the job course.
	 *
	 * @return The job course status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the status of the job course.
	 *
	 * @param status The job course status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
