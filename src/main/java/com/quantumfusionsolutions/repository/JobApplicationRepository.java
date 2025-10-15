package com.quantumfusionsolutions.repository;

import com.quantumfusionsolutions.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, String> {

    List<JobApplication> findByUserId(String userId);

}
