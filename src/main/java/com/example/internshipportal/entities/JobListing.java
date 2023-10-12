package com.example.internshipportal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "Jobs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "company"})})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobListing {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(name = "company")
    private String companyName;

    private String description;

    @ManyToOne
    private User author;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<JobApplication> applications;
}
