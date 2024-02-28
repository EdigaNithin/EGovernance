package com.governance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.governance.DTO.NewsDto;
import com.governance.entity.News;
import com.governance.repository.NewsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

	@Autowired
	private NewsRepository newsRepository;

	public List<News> getAllNews() {
		return newsRepository.findAll();
	}

	public Optional<News> getNewsById(Long newsId) {
		return newsRepository.findById(newsId);
	}

	public Optional<News> getNewsByTitle(String title) {
		return newsRepository.findByTitle(title);
	}

	public News createNews(NewsDto newsDto) {
		News createdNews = new News();

		createdNews.setTitle(newsDto.getTitle());
		createdNews.setContent(newsDto.getContent());
		createdNews.setPublishDate(newsDto.getPublishDate());

		createdNews = newsRepository.save(createdNews);

		return createdNews;
	}

	public boolean deleteNews(Long newsId) {
		if (newsRepository.existsById(newsId)) {
			newsRepository.deleteById(newsId);
			return true;
		} else {
			return false;
		}
	}

}
