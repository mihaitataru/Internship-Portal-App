package com.example.internshipportal.services;

import com.example.internshipportal.dto.JobListingDTO;
import com.example.internshipportal.entities.JobApplication;
import com.example.internshipportal.entities.JobListing;
import com.example.internshipportal.entities.User;
import com.example.internshipportal.repositories.JobApplicationRepository;
import com.example.internshipportal.repositories.JobListingRepository;
import com.example.internshipportal.validators.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobListingRepository jobListingRepository;

    private final JobApplicationRepository jobApplicationRepository;

    private final UserService userService;

    private final List<Validator<JobListingDTO>> jobValidators;

    private final List<Validator<JobApplication>> jobApplicationValidators;

    public List<JobListing> getJobListings() {
        return jobListingRepository.findAll();
    }
    public List<JobListing> getJobListings(String username) {
        return jobListingRepository.findAllByAuthor(userService.getUser(username));
    }


    public JobListing addJob(JobListingDTO job) {

        try{
            jobValidators.forEach(validator -> validator.validate(job));
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }

        var existingJob = JobListing.builder()
                        .title(job.getTitle())
                        .companyName(job.getCompanyName())
                        .description(job.getDescription()).author(userService.getUser(job.getEmployerName()))
                        .build();

        jobListingRepository.save(existingJob);
        return existingJob;
    }

    public void deleteJob(long id){
        var job = jobListingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Job does not exist"));

        jobListingRepository.delete(job);
    }

    private void addApplication(JobApplication jobApplication) {
        var job = jobListingRepository.findById(jobApplication.getJobId()).orElseThrow();

        jobApplicationRepository.save(jobApplication);
        job.getApplications().add(jobApplication);
        jobListingRepository.save(job);

    }
    
    public void validateAndAddApplication(JobApplication application) {
        try{
            jobApplicationValidators.forEach(validator -> validator.validate(application));
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        addApplication(application);
    }

    public List<User> getApplicantsForEmployer(String username) {
        List<JobListing> jobsForEmployer = getJobListings(username);
        List<User> response = new ArrayList<>();

        for(JobListing job: jobsForEmployer) {
            job.getApplications().forEach(application -> response.add(userService.getUser(application.getUsername())));
        }

        return response;
    }

    public List<User> getApplicantsForJob(String request) {
        long jobId;
        try {
             jobId = Long.parseLong(request);
        } catch (Exception e) {
            return getApplicantsForEmployer(request);
        }

        List<JobListing> jobs = jobListingRepository.findAllById(Collections.singleton(jobId));
        List<User> response = new ArrayList<>();

        for(JobListing job: jobs) {
            job.getApplications().forEach(application -> response.add(userService.getUser(application.getUsername())));
        }

        return response;
    }
}
