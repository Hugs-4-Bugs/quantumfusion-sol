package com.quantumfusionsolutions.service;

import com.quantumfusionsolutions.model.JobApplication;
import com.quantumfusionsolutions.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public JobApplication applyForJob(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    public List<JobApplication> getApplicationsByUserId(String userId) {
        return jobApplicationRepository.findByUserId(userId);
    }
}
