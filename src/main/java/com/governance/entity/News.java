package com.governance.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NewsUpdate")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long newsId;

	private String title;

	private String content;

	private Date publishDate;

	public Long getNewsId() {
		return newsId;
	}

	/**
	 * Sets the unique identifier of the news update.
	 *
	 * @param newsId The news update's unique identifier.
	 */
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

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
