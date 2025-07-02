package com.demo.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.test.entity.JobEntity;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
}
