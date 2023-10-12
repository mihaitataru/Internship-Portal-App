package com.example.internshipportal.validators.job;

import com.example.internshipportal.dto.JobListingDTO;
import com.example.internshipportal.services.UserService;
import com.example.internshipportal.validators.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmployerNameValidator implements Validator<JobListingDTO> {
    private final UserService userService;

    /**
     *
     * @param job Input from employer
     * @throws IllegalArgumentException if the employer name does not exist
     */
    @Override
    public void validate(JobListingDTO job) {
        try{
            userService.getUser(job.getEmployerName());
        } catch (UsernameNotFoundException e) {
            throw new IllegalArgumentException("Employer name is not valid");
        }
    }
}
