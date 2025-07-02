package com.demo.test.serviceImp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.test.entity.JobEntity;
import com.demo.test.repository.JobApplicationRepository;
import com.demo.test.repository.JobRepository;
import com.demo.test.request.JobApplicationRequest;
import com.demo.test.request.JobRequest;
import com.demo.test.service.EmployeService;
import com.demo.test.utilities.commonQueryAPIUtils;

@Service
public class EmployeServiceImp implements EmployeService {

	@Autowired
	private JobRepository jobRepo;

	@Autowired
	private JobApplicationRepository jobApplicationRepo;

	public ResponseEntity<?> createJob(JobRequest jobRequest) {
		try {
			// You could add validation for duplicate jobs here if needed
			// For example, check if the same employer already posted the same job title
			String errorMsg = commonQueryAPIUtils.validationService(
					Arrays.asList(jobRequest.getTitle(), jobRequest.getCompany(), jobRequest.getJobType(),
							jobRequest.getSkills(), jobRequest.getExperienceLevel()),
					Arrays.asList("Job Title", "Company name", "Job type", "Skills", "Experience level"));
			if (errorMsg.length() > 0) {
				return commonQueryAPIUtils.fStaticResponse(errorMsg);
			} else {

				if (jobRequest.getMinSalary() != null && jobRequest.getMaxSalary() != null
						&& jobRequest.getMinSalary() > jobRequest.getMaxSalary()) {
					return commonQueryAPIUtils.fStaticResponse("Minimum salary cannot be greater than maximum salary");
				} else {

					JobEntity entity = new JobEntity();
					entity.setEmployeId(jobRequest.getEmployeId());
					entity.setTitle(jobRequest.getTitle().trim());
					entity.setDescription(jobRequest.getDescription().trim());
					entity.setCompany(jobRequest.getCompany().trim());
					entity.setLocation(jobRequest.getLocation().trim());
					entity.setCompanyDescription(
							jobRequest.getCompanyDescription() != null ? jobRequest.getCompanyDescription().trim()
									: null);
					entity.setJobType(jobRequest.getJobType().trim());
					//entity.setSkills(jobRequest.getSkills().trim());
					entity.setMinSalary(jobRequest.getMinSalary());
					entity.setMaxSalary(jobRequest.getMaxSalary());
					entity.setExperienceLevel(jobRequest.getExperienceLevel().trim());
					entity.setApplicationDeadline(jobRequest.getApplicationDeadline());
					jobRepo.save(entity);

					return commonQueryAPIUtils.sResponse("Job posted successfully");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return commonQueryAPIUtils.fCatchResponse(e);
		}
	}

	public ResponseEntity<?> updateJobStatus(JobApplicationRequest jobApplicationRequest) {
		try {
			// You could add validation for duplicate jobs here if needed
			// For example, check if the same employer already posted the same job title
			String errorMsg = commonQueryAPIUtils.validationService(
					Arrays.asList(jobApplicationRequest.getJobId(), jobApplicationRequest.getJobSeekerId()),
					Arrays.asList("Job Title", "Company name"));
			if (errorMsg.length() > 0) {
				return commonQueryAPIUtils.fStaticResponse(errorMsg);
			} else {

				Integer count = jobApplicationRepo.updateJobStatus(jobApplicationRequest.getStatus(),
						jobApplicationRequest.getReasonForStatus(), jobApplicationRequest.getStatusUpdatedBy(),
						jobApplicationRequest.getJobId(), jobApplicationRequest.getJobSeekerId());

				if (count != null && count > 0) {
					return commonQueryAPIUtils.sResponse("Job status updated successfully");
				} else {
					return commonQueryAPIUtils
							.fStaticResponse("No records updated. Please check job ID and job seeker ID.");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return commonQueryAPIUtils.fCatchResponse(e);
		}
	}

}
