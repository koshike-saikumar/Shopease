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

import com.demo.test.repository.EmployeRepository;
import com.demo.test.request.JobApplicationRequest;
import com.demo.test.request.JobRequest;
import com.demo.test.service.EmployeService;
import com.demo.test.utilities.commonQueryAPIUtils;

@RestController
@CrossOrigin
public class EmployeController {

	@Autowired
	private EmployeRepository repo;

	@Autowired
	private EmployeService service;

	@PostMapping("/create-job")
	public ResponseEntity<?> createJob(@RequestBody JobRequest jobRequest) {
		return service.createJob(jobRequest);
	}

	@GetMapping("/jobs")
	public Map<String, Object> jobs(@RequestParam Integer employeId) {
		return commonQueryAPIUtils.apiService("jobs", repo.jobs(employeId));
	}

	@GetMapping("/job")
	public Map<String, Object> job(@RequestParam Integer employeId, @RequestParam Integer id) {
		return commonQueryAPIUtils.apiService("jobs", repo.job(employeId, id));
	}

	@GetMapping("/job-applications")
	public Map<String, Object> jobApplication(@RequestParam Integer id) {
		return commonQueryAPIUtils.apiService("jobs", repo.jobApplication(id));
	}

	@PostMapping("/update-status")
	public ResponseEntity<?> updateJobStatus(@RequestBody JobApplicationRequest JobApplicationRequest) {
		return service.updateJobStatus(JobApplicationRequest);
	}

}
