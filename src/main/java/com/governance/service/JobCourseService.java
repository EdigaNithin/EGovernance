package com.governance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.governance.entity.JobCourse;
import com.governance.repository.JobCourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JobCourseService {

	@Autowired
	private JobCourseRepository jobCourseRepository;

	public List<JobCourse> getAllJobCourses() {
		return jobCourseRepository.findAll();
	}

	public Optional<JobCourse> getJobCourseById(Long jobCourseId) {
		return jobCourseRepository.findById(jobCourseId);
	}

	public Optional<JobCourse> getJobCourseByTitle(String title) {
		return jobCourseRepository.findByTitle(title);
	}

	public JobCourse createOrUpdateJobCourse(JobCourse jobCourse) {
		return jobCourseRepository.save(jobCourse);
	}

	public void importJobCourseIfNotExists(String title) {
		Optional<JobCourse> existingJobCourse = jobCourseRepository.findByTitle(title);

		if (existingJobCourse.isEmpty()) {
			JobCourse newJobCourse = new JobCourse();
			newJobCourse.setTitle(title);
			newJobCourse.setStatus("Open");
			jobCourseRepository.save(newJobCourse);
		}
	}
}
