package com.demo.test.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.test.request.JobApplicationRequest;
import com.demo.test.request.JobRequest;

@Service
public interface EmployeService {

	public ResponseEntity<?> createJob(JobRequest jobRequest);
	
	public ResponseEntity<?> updateJobStatus(JobApplicationRequest JobApplicationRequest);

}
