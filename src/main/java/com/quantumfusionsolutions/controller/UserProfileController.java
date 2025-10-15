package com.quantumfusionsolutions.controller;

import com.quantumfusionsolutions.dto.UserProfileResponse;
import com.quantumfusionsolutions.model.Job;
import com.quantumfusionsolutions.model.UserProfile;
import com.quantumfusionsolutions.service.JobRecommendationService;
import com.quantumfusionsolutions.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private JobRecommendationService jobRecommendationService;

    @PostMapping
    public UserProfile createOrUpdateProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.createOrUpdateProfile(userProfile);
    }

    @GetMapping("/user/{userId}")
    public UserProfile getProfileByUserId(@PathVariable String userId) {
        return userProfileService.getProfileByUserId(userId);
    }

    @GetMapping("/user/{userId}/recommendations")
    public UserProfileResponse getProfileWithRecommendations(@PathVariable String userId) throws IOException {
        UserProfile userProfile = userProfileService.getProfileByUserId(userId);
        List<Job> recommendedJobs = jobRecommendationService.recommendJobs(userProfile);
        return new UserProfileResponse(userProfile, recommendedJobs);
    }
}
