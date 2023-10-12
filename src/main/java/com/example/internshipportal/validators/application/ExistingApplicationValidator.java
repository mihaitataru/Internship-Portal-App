package com.example.internshipportal.validators.application;

import com.example.internshipportal.entities.JobApplication;
import com.example.internshipportal.repositories.JobApplicationRepository;
import com.example.internshipportal.validators.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistingApplicationValidator implements Validator<JobApplication> {

    private final JobApplicationRepository jobApplicationRepository;

    /**
     * @param application the JobApplication object to validate
     * @throws IllegalArgumentException if the application already exists
     */
    @Override
    public void validate(JobApplication application) {
        var jobApp = jobApplicationRepository.findByJobId(application.getJobId()).orElse(null);
        if(jobApp != null && jobApp.getUsername().equals(application.getUsername())) {
            throw new IllegalArgumentException("Application already exist");
        }
    }
}
