package com.quantumfusionsolutions.controller;

import com.quantumfusionsolutions.model.JobApplication;
import com.quantumfusionsolutions.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping
    public JobApplication applyForJob(@RequestBody JobApplication jobApplication) {
        return jobApplicationService.applyForJob(jobApplication);
    }

    @GetMapping("/user/{userId}")
    public List<JobApplication> getApplicationsByUserId(@PathVariable String userId) {
        return jobApplicationService.getApplicationsByUserId(userId);
    }
}
