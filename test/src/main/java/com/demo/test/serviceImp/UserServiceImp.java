package com.demo.test.serviceImp;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.test.entity.UserEntity;
import com.demo.test.repository.UserRepository;
import com.demo.test.request.UserRequest;
import com.demo.test.service.userService;
import com.demo.test.utilities.commonQueryAPIUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImp implements userService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public ResponseEntity<?> loginUser(UserRequest reqBody) {
		try {
			String errorMsg = commonQueryAPIUtils.validationService(
					Arrays.asList(reqBody.getEmail(), reqBody.getPassword()), Arrays.asList("email", "password"));
			if (errorMsg.length() > 0) {
				return commonQueryAPIUtils.fStaticResponse(errorMsg);
			} else {
				String email = reqBody.getEmail();
				String password = reqBody.getPassword();

				List<Map<String, Object>> getUserDetails = userRepo.getUserDetails(email, password);
				if (getUserDetails.size() > 0) {
					Map<String, Object> user = getUserDetails.get(0);
					String token = generateJwtToken(user);
					// You might want to return the token in the response
					Map<String, String> response = new HashMap<>();

					response.put("status", "true");
					response.put("code", "01");
					response.put("message", "Login successful");
					response.put("token", token);
					return ResponseEntity.ok(response);
//					return commonQueryAPIUtils.sResponse(" Login successful");

				} else {
					System.err.println("user fail");
					return commonQueryAPIUtils.manualResponse("02", "Invalid credentials!");
				}

			}
		} catch (Exception e) {

			return commonQueryAPIUtils.manualResponse("02", "Invalid credentials!");
		}
	}

	private String generateJwtToken(Map<String, Object> user) {
		// For production, load this from secure configuration
		String secretKey = "Cr3FHTz5qBZVBO04qrqjppCanz0RSyzE0bHJGXHW/p0=";

		// Validate key length (HS256 requires 256-bit/32-byte key)
		if (secretKey.getBytes(StandardCharsets.UTF_8).length < 32) {
			throw new IllegalArgumentException("Secret key must be at least 32 characters long");
		}

		// Token expiration (3 hours)
		long expirationTime = 1000 * 60 * 60 * 3;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

		// Build claims
		Claims claims = Jwts.claims().setSubject(user.get("email").toString()).setExpiration(expirationDate);

		claims.put("userId", user.get("id"));
		claims.put("role", user.get("role"));
		claims.put("name", user.get("name"));
		claims.put("email", user.get("email"));


		// Generate token
		return Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8)).compact();
	}

	public ResponseEntity<?> createUser(UserRequest user) {
		String message = "";

		try {
			int count = userRepo.alredyRegister(user.getEmail().trim().toLowerCase());
			System.err.println(count);
			if (count > 0) {
				return commonQueryAPIUtils.fDynamicResponse("User already exists with this email");
			} else {

				UserEntity entity = new UserEntity();
				entity.setEmail(user.getEmail().trim().toLowerCase());
				entity.setName(user.getName().trim());
				entity.setPassword(user.getPassword().trim());
				entity.setRole(user.getRole());

				userRepo.save(entity);

				return commonQueryAPIUtils.sResponse(" User created successfully");
			}

		} catch (Exception e) {
			return commonQueryAPIUtils.fCatchResponse(e);
		}
	}

	public ResponseEntity<?> updateProfile(UserRequest user) {
		String message = "";

		try {
			String password = user.getPassword();
			String name = user.getName().trim();
			Long Id = user.getId();
			Integer updateWithPassword = 0;

			Integer updateWithOutPassword = userRepo.updateWithOutPassword(Id, name);

			if (password != null) {
				updateWithPassword = userRepo.updateWithPassword(Id, name, password);
			}
			if (updateWithOutPassword > 0 || updateWithPassword > 0) {
				return commonQueryAPIUtils.sResponse("User created successfully");
			} else {
				return commonQueryAPIUtils.fDynamicResponse("Something went wrong; the update did not occur.");

			}

		} catch (Exception e) {
			e.printStackTrace();
			return commonQueryAPIUtils.fCatchResponse(e);
		}
	}

}
