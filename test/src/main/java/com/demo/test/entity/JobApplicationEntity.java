package com.demo.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "job_applications")
@Data
public class JobApplicationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "job_id", nullable = false)
	private Integer jobId;

	@Column(name = "job_seeker_id", nullable = false)
	private Integer jobSeekerId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "resume_url", nullable = false)
	private String resumeUrl;

	@Column(name = "cover_letter")
	private String coverLetter;

	@Column(name = "applied_by", nullable = false)
	private String appliedBy;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "experience", nullable = false)
	private String experience;

	@Column(name = "education", nullable = false)
	private String education;

}
