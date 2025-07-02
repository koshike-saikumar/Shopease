package com.demo.test.serviceImp;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.test.entity.JobApplicationEntity;
import com.demo.test.repository.JobApplicationRepository;
import com.demo.test.request.JobApplicationRequest;
import com.demo.test.service.JobseekerService;
import com.demo.test.utilities.commonQueryAPIUtils;

@Service
public class JobseekerServiceImp implements JobseekerService {

	@Autowired
	JobApplicationRepository jobApplicationRepo;

	@Override
	public ResponseEntity<?> createJobApplication(@RequestBody JobApplicationRequest jobApplicationRequest) {
		try {
			// Validate required fields
			String errorMsg = commonQueryAPIUtils.validationService(
					Arrays.asList(jobApplicationRequest.getJobId(), jobApplicationRequest.getJobSeekerId(),
							jobApplicationRequest.getName(), jobApplicationRequest.getEmail(),
							jobApplicationRequest.getPhone(), jobApplicationRequest.getExperience(),
							jobApplicationRequest.getEducation(), jobApplicationRequest.getResumeUrl()),
					Arrays.asList("Job ID", "Job Seeker ID", "Name", "Email", "Phone", "Experience", "Education",
							"Resume URL"));

			if (errorMsg.length() > 0) {
				return commonQueryAPIUtils.fStaticResponse(errorMsg);
			} else {
				JobApplicationEntity entity = new JobApplicationEntity();
				entity.setJobId(jobApplicationRequest.getJobId());
				entity.setJobSeekerId(jobApplicationRequest.getJobSeekerId());
				entity.setName(jobApplicationRequest.getName().trim());
				entity.setEmail(jobApplicationRequest.getEmail().trim());
				entity.setPhone(jobApplicationRequest.getPhone().trim());
				entity.setExperience(jobApplicationRequest.getExperience().trim());
				entity.setEducation(jobApplicationRequest.getEducation().trim());
				entity.setResumeUrl(jobApplicationRequest.getResumeUrl().trim());
				entity.setCoverLetter(
						jobApplicationRequest.getCoverLetter() != null ? jobApplicationRequest.getCoverLetter().trim()
								: null);
				entity.setAppliedBy(
						jobApplicationRequest.getAppliedBy() != null ? jobApplicationRequest.getAppliedBy().trim()
								: "user");

				jobApplicationRepo.save(entity);
				jobApplicationRepo.save(entity);

				return commonQueryAPIUtils.sResponse("Job application submitted successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return commonQueryAPIUtils.fCatchResponse(e);
		}
	}

}
