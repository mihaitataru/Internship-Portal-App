package com.example.internshipportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobListingDTO {
    private String title;

    private String companyName;

    private String description;

    private String employerName;
}
