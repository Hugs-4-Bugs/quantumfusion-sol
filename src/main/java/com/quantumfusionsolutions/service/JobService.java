package com.quantumfusionsolutions.service;

import com.quantumfusionsolutions.model.Job;
import com.quantumfusionsolutions.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(String jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }
}
