package com.governance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.governance.DTO.NewsDto;
import com.governance.entity.News;

import jakarta.validation.Valid;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
	Optional<News> findByTitle(String title);

	News save(@Valid NewsDto newsDto);

}
