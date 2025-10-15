package com.quantumfusionsolutions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;

@Entity
public class JobApplication {

    @Id
    private String applicationId;
    private String userId;
    private String jobId;
    private String jobTitle;
    private String fullName;
    private String email;
    private String phone;
    private String resumeFileName;
    private String resumeStoragePath;
    private String linkedinProfile;
    private String portfolioWebsite;
    private String codingProfileUrl;
    private String highestQualification;
    private String specialization;
    private String university;
    private String passingYear;
    private String marks;
    private String primarySkills;
    private String secondarySkills;
    private boolean isFresher;
    private String companyName;
    private String jobTitleExperience;
    private String fromDate;
    private String toDate;
    private boolean isCurrentlyWorking;
    private String jobDescription;
    private String coverLetter;
    private String preferredJobLocation;
    private String currentCTC;
    private String expectedSalary;
    private String noticePeriod;
    private boolean acceptedTerms;
    private boolean notifiedForSimilarJobs;
    private Timestamp appliedAt;

    // Getters and setters
}