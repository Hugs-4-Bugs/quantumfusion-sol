package com.quantumfusionsolutions.controller;

import com.quantumfusionsolutions.model.Job;
import com.quantumfusionsolutions.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{jobId}")
    public Job getJobById(@PathVariable String jobId) {
        return jobService.getJobById(jobId);
    }
}
