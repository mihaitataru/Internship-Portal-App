package com.example.internshipportal.validators.application;

import com.example.internshipportal.entities.JobApplication;
import com.example.internshipportal.services.UserService;
import com.example.internshipportal.validators.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicantValidator implements Validator<JobApplication> {

    private final UserService userService;

    /**
     *
     * @param application the JobApplication object to validate
     * @throws IllegalArgumentException if the applicant username does not exist
     */
    @Override
    public void validate(JobApplication application) {
        try{
            userService.getUser(application.getUsername());
        } catch (UsernameNotFoundException e){
            throw new IllegalArgumentException("Applicant does not exist");
        }
    }
}
