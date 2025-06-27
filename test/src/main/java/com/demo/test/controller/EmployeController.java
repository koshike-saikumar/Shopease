package com.demo.test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.test.repository.EmployeRepository;
import com.demo.test.repository.UserRepository;
import com.demo.test.utilities.commonQueryAPIUtils;

@RestController
@CrossOrigin
public class EmployeController {
	
	
	@Autowired
	private EmployeRepository repo;
	
	@GetMapping("/jobs")
	public Map<String, Object> userDetails(@RequestParam Integer employeId) {
		return commonQueryAPIUtils.apiService("jobs", repo.jobs(employeId));
	}
}
