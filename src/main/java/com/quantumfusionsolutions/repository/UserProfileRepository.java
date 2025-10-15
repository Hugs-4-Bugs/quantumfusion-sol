package com.quantumfusionsolutions.repository;

import com.quantumfusionsolutions.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    UserProfile findByUserId(String userId);

}
