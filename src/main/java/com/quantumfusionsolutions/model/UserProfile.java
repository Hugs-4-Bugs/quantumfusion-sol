package com.quantumfusionsolutions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.sql.Timestamp;

@Entity
public class UserProfile {

    @Id
    private String profileId;
    private String userId;
    private String fullName;
    private String phone;
    @Transient
    private String[] skills;
    private String skillsCsv;
    private String experience;
    private String education;
    private String resumeUrl;
    private String codingProfileUrl;
    private String linkedinProfile;
    private String portfolioWebsite;
    private String preferredJobLocation;
    private String currentCTC;
    private String expectedSalary;
    private String noticePeriod;
    private boolean jobNotificationOptIn;
    private Timestamp updatedAt;

    // Getters and setters

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] getSkills() {
        if (skillsCsv != null && !skillsCsv.isEmpty()) {
            return skillsCsv.split(",");
        }
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
        if (skills != null) {
            this.skillsCsv = String.join(",", skills);
        }
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getCodingProfileUrl() {
        return codingProfileUrl;
    }

    public void setCodingProfileUrl(String codingProfileUrl) {
        this.codingProfileUrl = codingProfileUrl;
    }

    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    public String getPortfolioWebsite() {
        return portfolioWebsite;
    }

    public void setPortfolioWebsite(String portfolioWebsite) {
        this.portfolioWebsite = portfolioWebsite;
    }

    public String getPreferredJobLocation() {
        return preferredJobLocation;
    }

    public void setPreferredJobLocation(String preferredJobLocation) {
        this.preferredJobLocation = preferredJobLocation;
    }

    public String getCurrentCTC() {
        return currentCTC;
    }

    public void setCurrentCTC(String currentCTC) {
        this.currentCTC = currentCTC;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(String noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public boolean isJobNotificationOptIn() {
        return jobNotificationOptIn;
    }

    public void setJobNotificationOptIn(boolean jobNotificationOptIn) {
        this.jobNotificationOptIn = jobNotificationOptIn;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
