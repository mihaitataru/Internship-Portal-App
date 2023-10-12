package com.example.internshipportal.validators.application;

import com.example.internshipportal.entities.JobApplication;
import com.example.internshipportal.validators.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class PhoneNumberValidator implements Validator<JobApplication> {

    private static final String PHONE_NUMBER_PATTERN = "^\\d{10}$";

    /**
     * @param application the JobApplication object to validate
     * @throws IllegalArgumentException if the phone number is not valid
     */
    @Override
    public void validate(JobApplication application) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        if (!pattern.matcher(application.getPhoneNumber()).matches()) {
            throw new IllegalArgumentException("This is not a phone number");
        }
    }
}
