package com.example.internshipportal.repositories;

import com.example.internshipportal.entities.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    public Optional<JobListing> findByTitle(String title);
}
