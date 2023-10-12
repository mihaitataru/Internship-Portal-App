package com.example.internshipportal.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Applications", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"job", "applicant"})})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "job")
    private Long jobId;

    @Column(name = "applicant")
    private String username;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String address;

    private String country;

    private String state;

    private String city;

}
