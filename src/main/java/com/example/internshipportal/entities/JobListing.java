package com.example.internshipportal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "Jobs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobListing {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String companyName;

    private String description;

    @OneToOne
    private User author;

    @OneToMany
    private List<JobApplication> applications;
}
