package com.governance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.governance.DTO.FeedBackDto;
import com.governance.entity.FeedBack;

public interface FeedBackRepository extends JpaRepository<FeedBack, String> {

	FeedBack save(FeedBackDto r);

}
