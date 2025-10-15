package com.quantumfusionsolutions.service;

import com.quantumfusionsolutions.model.UserProfile;
import com.quantumfusionsolutions.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile createOrUpdateProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public UserProfile getProfileByUserId(String userId) {
        return userProfileRepository.findByUserId(userId);
    }
}
