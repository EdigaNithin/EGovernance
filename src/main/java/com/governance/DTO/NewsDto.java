package com.governance.DTO;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for News entity.
 */
public class NewsDto {
	/**
	 * Title of the news update. It should not be blank.
	 * 
	 */
	@NotBlank(message = "Title is required")
	private String title;

	/**
	 * Content of the news update. It should not be blank.
	 */
	@NotBlank(message = "Content is required")
	private String content;

	/**
	 * Date when the news update was published.
	 */
	private Date publishDate;

	/**
	 * Retrieves the title of the news update.
	 *
	 * @return The title of the news update.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the news update.
	 *
	 * @param title The title of the news update.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Retrieves the content of the news update.
	 *
	 * @return The content of the news update.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content of the news update.
	 *
	 * @param content The content of the news update.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Retrieves the publish date of the news update.
	 *
	 * @return The publish date of the news update.
	 */
	public Date getPublishDate() {
		return publishDate;
	}

	/**
	 * Sets the publish date of the news update.
	 *
	 * @param publishDate The publish date of the news update.
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
}
