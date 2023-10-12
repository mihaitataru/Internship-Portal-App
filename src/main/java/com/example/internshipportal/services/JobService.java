package com.example.internshipportal.services;

import com.example.internshipportal.dto.JobListingDTO;
import com.example.internshipportal.entities.JobApplication;
import com.example.internshipportal.entities.JobListing;
import com.example.internshipportal.repositories.JobApplicationRepository;
import com.example.internshipportal.repositories.JobListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobListingRepository jobListingRepository;

    private final JobApplicationRepository jobApplicationRepository;

    private final UserService userService;

    public List<JobListing> getJobListings() {
        return jobListingRepository.findAll();
    }

    public JobListing addJob(JobListingDTO job) {
        var existingJob = jobListingRepository.findByTitle(job.getTitle()).orElse(null);
        if(existingJob != null) {
            throw new IllegalArgumentException("Job already exists");
        }

        existingJob = JobListing.builder()
                        .title(job.getTitle())
                        .companyName(job.getCompanyName())
                        .description(job.getDescription()).author(userService.getUser(job.getEmployerName()))
                        .build();

        jobListingRepository.save(existingJob);
        return existingJob;
    }

    private void addApplication(JobApplication jobApplication) {
        var job = jobListingRepository.findById(jobApplication.getJobId()).orElseThrow();

        if(job.getApplications().contains(jobApplication))
            throw new IllegalArgumentException("Application already exists");

        jobApplicationRepository.save(jobApplication);
        job.getApplications().add(jobApplication);
        jobListingRepository.save(job);

    }
    
    public void validateAndAddApplication(JobApplication application) {
        addApplication(application);
    }

}
