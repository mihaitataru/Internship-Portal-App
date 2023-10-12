package com.example.internshipportal.controllers;

import com.example.internshipportal.dto.JobListingDTO;
import com.example.internshipportal.entities.JobApplication;
import com.example.internshipportal.entities.JobListing;
import com.example.internshipportal.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobListingService;

    @GetMapping
    public ResponseEntity<List<JobListing>> getJobs() {
        try{
            List<JobListing> jobs = jobListingService.getJobListings();
            return ResponseEntity.ok(jobs);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    public ResponseEntity<String> addJob(@RequestBody JobListingDTO jobListing) {
        if(jobListing == null) {
            return new ResponseEntity<>("Invalid body", HttpStatus.BAD_REQUEST);
        }
        try{
            jobListingService.addJob(jobListing);
            return new ResponseEntity<>("Job created successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path="/apply")
    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    public ResponseEntity<String> addApplication(@RequestBody JobApplication application) {
        try {
            jobListingService.validateAndAddApplication(application);
            return new ResponseEntity<>("Application created successfully", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
