package com.demo.test.request;

import lombok.Data;

@Data
public class JobApplicationRequest {

	private Integer jobId;
	private Integer jobSeekerId;
	private String name;
	private String email;
	private String resumeUrl;
	private String coverLetter;
	private String appliedBy;
	private String status;
	private String reasonForStatus;
	private String statusUpdatedBy;

	private String phone;

	private String experience;

	private String education;

}
