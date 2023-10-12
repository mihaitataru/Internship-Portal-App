package com.example.internshipportal.validators.application;

import com.example.internshipportal.entities.JobApplication;
import com.example.internshipportal.repositories.JobListingRepository;
import com.example.internshipportal.validators.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobIdValidator implements Validator<JobApplication> {

    private final JobListingRepository jobListingRepository;

    /**
     *
     * @param application the JobApplication object to validate
     * @throws IllegalArgumentException if the job id does not exist
     */
    @Override
    public void validate(JobApplication application) {
        jobListingRepository.findById(application.getJobId()).orElseThrow(() -> new IllegalArgumentException("Job id does not exist"));
    }
}
