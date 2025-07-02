package com.demo.test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.test.repository.JobseekerRepository;
import com.demo.test.request.JobApplicationRequest;
import com.demo.test.service.JobseekerService;
import com.demo.test.utilities.commonQueryAPIUtils;

@RestController
@CrossOrigin
public class JobseekerController {

	@Autowired
	JobseekerRepository repo;

	@Autowired
	JobseekerService service;

	@PostMapping("/apply-job")
	public ResponseEntity<?> applyForJob(@RequestBody JobApplicationRequest jobApplicationRequest) {
		return service.createJobApplication(jobApplicationRequest);
	}

	@GetMapping("/search-jobs")
	public Map<String, Object> searchJobs(@RequestParam Integer id) {
		return commonQueryAPIUtils.apiService("jobs", repo.searchJobs(id));
	}

	@GetMapping("/applied-jobs")
	public Map<String, Object> appliedJobs(@RequestParam Integer jobSeekerId) {
		return commonQueryAPIUtils.apiService("data", repo.appliedJobs(jobSeekerId));
	}

	@GetMapping("/basedon-skills")
	public Map<String, Object> basedOnSkills(@RequestParam String skills, @RequestParam Integer jobSeekerId) {
		return commonQueryAPIUtils.apiService("data", repo.basedOnSkills(skills, jobSeekerId));
	}

}
