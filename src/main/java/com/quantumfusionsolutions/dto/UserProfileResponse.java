package com.quantumfusionsolutions.dto;

import com.quantumfusionsolutions.model.Job;
import com.quantumfusionsolutions.model.UserProfile;

import java.util.List;

public class UserProfileResponse {

    private UserProfile userProfile;
    private List<Job> recommendedJobs;

    public UserProfileResponse(UserProfile userProfile, List<Job> recommendedJobs) {
        this.userProfile = userProfile;
        this.recommendedJobs = recommendedJobs;
    }

    // Getters

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public List<Job> getRecommendedJobs() {
        return recommendedJobs;
    }
}
