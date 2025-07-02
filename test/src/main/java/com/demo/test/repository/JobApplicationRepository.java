package com.demo.test.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.test.entity.JobApplicationEntity;

import jakarta.transaction.Transactional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Integer> {

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = """
						UPDATE job_applications
			SET
			  status =:status,                            -- or 'accepted', 'rejected', etc.
			  status_reason =:status_reason, -- your custom reason
			  status_updated_at = now(),
			  status_updated_by =:status_updated_by                            -- ID of the employer or user performing the update
			WHERE job_id =:job_id AND job_seeker_id =:job_seeker_id;
						""")
	Integer updateJobStatus(@Param("status") String status, @Param("status_reason") String statusReason,
			@Param("status_updated_by") String statusUpdatedBy, @Param("job_id") Integer jobId,
			@Param("job_seeker_id") Integer jobSeekerId);

}
