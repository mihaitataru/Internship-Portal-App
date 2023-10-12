package com.example.internshipportal.repositories;

import com.example.internshipportal.entities.JobListing;
import com.example.internshipportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    public Optional<JobListing> findByTitle(String title);

    public Optional<JobListing> findByTitleAndCompanyName(String title, String companyName);

    public List<JobListing> findAllByAuthor(User user);
}
