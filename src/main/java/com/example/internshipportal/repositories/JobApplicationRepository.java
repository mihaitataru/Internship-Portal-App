package com.example.internshipportal.repositories;

import com.example.internshipportal.entities.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    public Optional<JobApplication> findByJobId(Long id);

    public Optional<JobApplication> findByUsername(String userName);

}
