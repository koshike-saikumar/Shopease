package com.demo.test.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.test.request.JobApplicationRequest;

@Service
public interface JobseekerService {
	
	ResponseEntity<?> createJobApplication( JobApplicationRequest jobApplicationRequest);

}
