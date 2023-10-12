package com.example.internshipportal.validators.application;

import com.example.internshipportal.entities.JobApplication;
import com.example.internshipportal.validators.Validator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ApplicationValidator implements Validator<JobApplication> {
    /**
     *
     * @param application the JobApplication object to validate
     * @throws IllegalArgumentException if the application does not have the required fields
     */
    @Override
    public void validate(JobApplication application) {
        for(Field field: application.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(application);
                if (!field.getName().equals("id") && value == null) {
                    throw new IllegalArgumentException("Application does not have all the required fields\nMissing field: " + field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
