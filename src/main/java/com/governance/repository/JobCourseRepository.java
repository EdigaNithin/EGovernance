package com.governance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.governance.entity.JobCourse;

import java.util.Optional;

public interface JobCourseRepository extends JpaRepository<JobCourse, Long> {
	Optional<JobCourse> findByTitle(String title);
}
