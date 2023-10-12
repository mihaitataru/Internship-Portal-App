package com.example.internshipportal.services;

import com.example.internshipportal.entities.JobApplication;
import com.example.internshipportal.entities.JobListing;
import com.example.internshipportal.entities.Role;
import com.example.internshipportal.entities.User;
import com.example.internshipportal.repositories.JobApplicationRepository;
import com.example.internshipportal.repositories.JobListingRepository;
import com.example.internshipportal.repositories.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class JobServiceTest {

    @Autowired
    private JobService jobService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JobListingRepository jobListingRepository;

    @MockBean
    private JobApplicationRepository jobApplicationRepository;

    private static User userApplicant;

    private static User userEmployer;

    private static JobApplication jobApplication;

    private static JobListing job1;
    private static JobListing job2;

    @BeforeAll
    static void setUp(){
        userEmployer = User.builder().id(1L)
                .username("user1")
                .firstName("Employer")
                .lastName("User")
                .password("1234")
                .role(Role.ROLE_EMPLOYER)
                .build();

        userApplicant = User.builder().id(2L)
                .username("user2")
                .firstName("Applicant")
                .lastName("User")
                .password("1234")
                .role(Role.ROLE_APPLICANT)
                .build();

        jobApplication = JobApplication.builder()
                .jobId(1L)
                .id(1L)
                .username(userApplicant.getUsername())
                .firstName(userApplicant.getFirstName())
                .lastName(userApplicant.getLastName())
                .email("a@a.a")
                .phoneNumber("1234567890")
                .address("Street")
                .country("Canada")
                .state("Ontario")
                .city("City")
                .build();

        job1 = JobListing.builder()
                .id(1L)
                .title("Software Engineer")
                .companyName("Amazon")
                .description("Software engineer at Amazon")
                .author(userEmployer)
                .applications(List.of(jobApplication))
                .build();

        job2 = JobListing.builder()
                .id(2L)
                .title("Senior Software Engineer")
                .companyName("Google")
                .description("Software engineer at Google")
                .author(userEmployer)
                .applications(new ArrayList<>())
                .build();
    }


    @Test
    void validateAndAddApplicationGood() {

        when(userRepository.findByUsername("user1")).thenReturn(Optional.ofNullable(userEmployer));
        when(userRepository.findByUsername("user2")).thenReturn(Optional.ofNullable(userApplicant));

        when(jobListingRepository.findById(1L)).thenReturn(Optional.ofNullable(job1));
        when(jobListingRepository.findById(2L)).thenReturn(Optional.ofNullable(job2));
        when(jobApplicationRepository.findById(1L)).thenReturn(Optional.ofNullable(jobApplication));

        JobApplication application = JobApplication.builder()
                .jobId(2L)
                .id(2L)
                .username(userApplicant.getUsername())
                .firstName(userApplicant.getFirstName())
                .lastName(userApplicant.getLastName())
                .email("a@a.a")
                .phoneNumber("1234567890")
                .address("Street")
                .country("Romania")
                .state("Maramures")
                .city("Baia Mare")
                .build();

        assertDoesNotThrow(() -> jobService.validateAndAddApplication(application));
    }

    @Test
    void MissingArgumentsTest() {
        when(userRepository.findByUsername("user1")).thenReturn(Optional.ofNullable(userEmployer));
        when(userRepository.findByUsername("user2")).thenReturn(Optional.ofNullable(userApplicant));

        when(jobListingRepository.findById(1L)).thenReturn(Optional.ofNullable(job1));
        when(jobListingRepository.findById(2L)).thenReturn(Optional.ofNullable(job2));
        when(jobApplicationRepository.findById(1L)).thenReturn(Optional.ofNullable(jobApplication));

        JobApplication application = JobApplication.builder()
                .jobId(2L)
                .id(2L)
                .username(userApplicant.getUsername())
                .firstName(userApplicant.getFirstName())
                .lastName(userApplicant.getLastName())
                .phoneNumber("1234567890")
                .address("Street")
                .country("Romania")
                .state("Maramures")
                .city("Baia Mare")
                .build();

        Exception e = assertThrows(IllegalArgumentException.class, () -> jobService.validateAndAddApplication(application));
        assertEquals("Application does not have all the required fields\nMissing field: email", e.getMessage());
    }

    @Test
    void invalidApplicantTest(){
        when(userRepository.findByUsername("user1")).thenReturn(Optional.ofNullable(userEmployer));
        when(userRepository.findByUsername("user2")).thenReturn(Optional.ofNullable(userApplicant));

        when(jobListingRepository.findById(1L)).thenReturn(Optional.ofNullable(job1));
        when(jobListingRepository.findById(2L)).thenReturn(Optional.ofNullable(job2));
        when(jobApplicationRepository.findById(1L)).thenReturn(Optional.ofNullable(jobApplication));

        JobApplication application = JobApplication.builder()
                .jobId(2L)
                .id(2L)
                .username("Not found")
                .firstName(userApplicant.getFirstName())
                .lastName(userApplicant.getLastName())
                .phoneNumber("1234567890")
                .address("Street")
                .country("Romania")
                .state("Maramures")
                .city("Baia Mare")
                .build();

        Exception e = assertThrows(IllegalArgumentException.class, () -> jobService.validateAndAddApplication(application));
        assertEquals("Applicant does not exist", e.getMessage());
    }

    @Test
    void invalidJobTest(){
        when(userRepository.findByUsername("user1")).thenReturn(Optional.ofNullable(userEmployer));
        when(userRepository.findByUsername("user2")).thenReturn(Optional.ofNullable(userApplicant));

        when(jobListingRepository.findById(1L)).thenReturn(Optional.ofNullable(job1));
        when(jobListingRepository.findById(2L)).thenReturn(Optional.ofNullable(job2));
        when(jobApplicationRepository.findById(1L)).thenReturn(Optional.ofNullable(jobApplication));

        JobApplication application = JobApplication.builder()
                .jobId(21L)
                .id(2L)
                .username(userApplicant.getUsername())
                .firstName(userApplicant.getFirstName())
                .lastName(userApplicant.getLastName())
                .email("a@a.a")
                .phoneNumber("1234567890")
                .address("Street")
                .country("Romania")
                .state("Maramures")
                .city("Baia Mare")
                .build();

        Exception e = assertThrows(IllegalArgumentException.class, () -> jobService.validateAndAddApplication(application));
        assertEquals("Job id does not exist", e.getMessage());
    }

    @Test
    void invalidEmailTest(){
        when(userRepository.findByUsername("user1")).thenReturn(Optional.ofNullable(userEmployer));
        when(userRepository.findByUsername("user2")).thenReturn(Optional.ofNullable(userApplicant));

        when(jobListingRepository.findById(1L)).thenReturn(Optional.ofNullable(job1));
        when(jobListingRepository.findById(2L)).thenReturn(Optional.ofNullable(job2));
        when(jobApplicationRepository.findById(1L)).thenReturn(Optional.ofNullable(jobApplication));

        JobApplication application = JobApplication.builder()
                .jobId(2L)
                .id(2L)
                .username(userApplicant.getUsername())
                .firstName(userApplicant.getFirstName())
                .lastName(userApplicant.getLastName())
                .email("a")
                .phoneNumber("1234567890")
                .address("Street")
                .country("Romania")
                .state("Maramures")
                .city("Baia Mare")
                .build();

        Exception e = assertThrows(IllegalArgumentException.class, () -> jobService.validateAndAddApplication(application));
        assertEquals("Email is not a valid email", e.getMessage());
    }

    @Test
    void invalidPhoneNumberTest() {
        when(userRepository.findByUsername("user1")).thenReturn(Optional.ofNullable(userEmployer));
        when(userRepository.findByUsername("user2")).thenReturn(Optional.ofNullable(userApplicant));

        when(jobListingRepository.findById(1L)).thenReturn(Optional.ofNullable(job1));
        when(jobListingRepository.findById(2L)).thenReturn(Optional.ofNullable(job2));
        when(jobApplicationRepository.findById(1L)).thenReturn(Optional.ofNullable(jobApplication));

        JobApplication application = JobApplication.builder()
                .jobId(2L)
                .id(2L)
                .username(userApplicant.getUsername())
                .firstName(userApplicant.getFirstName())
                .lastName(userApplicant.getLastName())
                .email("a@a.a")
                .phoneNumber("12")
                .address("Street")
                .country("Romania")
                .state("Maramures")
                .city("Baia Mare")
                .build();

        Exception e = assertThrows(IllegalArgumentException.class, () -> jobService.validateAndAddApplication(application));
        assertEquals("This is not a phone number", e.getMessage());
    }
}