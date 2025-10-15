package com.quantumfusionsolutions.repository;

import com.quantumfusionsolutions.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

}
