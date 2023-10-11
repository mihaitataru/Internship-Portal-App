package com.example.internshipportal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Applications")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    private Long id;

    private String jobName;

    private String firstName;

    private String lastName;

    private long phoneNumber;

    private String email;

    private String address;

    private String country;

    private String state;

    private String city;

}
