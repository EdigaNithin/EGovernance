package com.governance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.governance.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

	List<Application> findByUserId(Long userId);
}
