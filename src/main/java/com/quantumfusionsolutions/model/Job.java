package com.quantumfusionsolutions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Job {

    @Id
    private String jobId;
    private String title;
    private String company;
    private String location;
    private String department;
    private String description;
    private String employmentType;
    private String salaryRange;
    private String[] requiredSkills;
    private Timestamp postedDate;
    private boolean isActive;

    // Getters and setters


    public String getId() {
        return jobId;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }
}