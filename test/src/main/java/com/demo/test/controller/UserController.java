package com.demo.test.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.test.repository.UserRepository;
import com.demo.test.request.UserRequest;
import com.demo.test.service.userService;
import com.demo.test.utilities.commonQueryAPIUtils;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	userService userService;

	@GetMapping("/sai")
	String sai() {
		return "hi sai";

	}
	
	@GetMapping("/Generate-secret-key")
	private String generateSecureKey() throws NoSuchAlgorithmException {
	    KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
	    keyGenerator.init(256); // Key size for HS256
	    SecretKey secretKey = keyGenerator.generateKey();
	    return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}

	@Autowired
	private UserRepository userRepo;

	@PostMapping("/user-login")
	public ResponseEntity<?> loginUser(@RequestBody UserRequest user) {
		return userService.loginUser(user);
	}

	@PostMapping("/user-register")
	public ResponseEntity<?> createUser(@RequestBody UserRequest user) {
		return userService.createUser(user);
	}

	@GetMapping("/user-details")
	public Map<String, Object> userDetails(@RequestParam Integer id) {
		return commonQueryAPIUtils.apiService("user", userRepo.userDetails(id));
	}

	@PostMapping("/update-profile")
	public ResponseEntity<?> updateProfile(@RequestBody UserRequest user) {
		return userService.updateProfile(user);
	}

}
