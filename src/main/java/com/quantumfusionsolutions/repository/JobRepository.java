package com.quantumfusionsolutions.repository;

import com.quantumfusionsolutions.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}
