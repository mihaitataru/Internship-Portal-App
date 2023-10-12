package com.example.internshipportal.validators.job;

import com.example.internshipportal.dto.JobListingDTO;
import com.example.internshipportal.repositories.JobListingRepository;
import com.example.internshipportal.validators.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistingJobValidator implements Validator<JobListingDTO> {

    private final JobListingRepository jobListingRepository;

    /**
     *
     * @param t job to validate
     * @throws IllegalArgumentException if job listing already exists
     */
    @Override
    public void validate(JobListingDTO t) {
        var existingJob = jobListingRepository.findByTitle(t.getTitle()).orElse(null);
        if(existingJob != null && existingJob.getCompanyName().equals(t.getCompanyName())) {
            throw new IllegalArgumentException("Job already exists");
        }
    }
}
