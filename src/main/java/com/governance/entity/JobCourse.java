package com.governance.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.Date;

/**
 * Represents a job course entity with information such as title, description,
 * eligibility criteria, application deadline, and status.
 */
@Entity
@Table(name = "job_course")
public class JobCourse {

	/**
	 * Unique identifier for the job course.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobCourseId;

	/**
	 * Title of the job course.
	 */
	private String title;

	/**
	 * Description of the job course.
	 */
	private String description;

	/**
	 * Eligibility criteria for the job course.
	 */
	private String eligibilityCriteria;

	/**
	 * Application deadline for the job course.
	 */
	private Date applicationDeadline;

	/**
	 * Current status of the job course.
	 */
	private String status;

	/**
	 * Gets the unique identifier for the job course.
	 *
	 * @return The job course identifier.
	 */
	public Long getJobCourseId() {
		return jobCourseId;
	}

	/**
	 * Sets the unique identifier for the job course.
	 *
	 * @param jobCourseId The job course identifier to set.
	 */
	public void setJobCourseId(Long jobCourseId) {
		this.jobCourseId = jobCourseId;
	}

	/**
	 * Gets the title of the job course.
	 *
	 * @return The job course title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the job course.
	 *
	 * @param title The job course title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description of the job course.
	 *
	 * @return The job course description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the job course.
	 *
	 * @param description The job course description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the eligibility criteria for the job course.
	 *
	 * @return The job course eligibility criteria.
	 */
	public String getEligibilityCriteria() {
		return eligibilityCriteria;
	}

	/**
	 * Sets the eligibility criteria for the job course.
	 *
	 * @param eligibilityCriteria The job course eligibility criteria to set.
	 */
	public void setEligibilityCriteria(String eligibilityCriteria) {
		this.eligibilityCriteria = eligibilityCriteria;
	}

	/**
	 * Gets the application deadline for the job course.
	 *
	 * @return The job course application deadline.
	 */
	public Date getApplicationDeadline() {
		return applicationDeadline;
	}

	/**
	 * Sets the application deadline for the job course.
	 *
	 * @param applicationDeadline The job course application deadline to set.
	 */
	public void setApplicationDeadline(Date applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}

	/**
	 * Gets the current status of the job course.
	 *
	 * @return The job course status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the current status of the job course.
	 *
	 * @param status The job course status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
