package com.demo.test.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobRequest {
    @NotNull(message = "Employee ID is required")
    private Long employeId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Company name is required")
    private String company;

    @NotBlank(message = "Location is required")
    private String location;

    private String companyDescription;
    
    @NotBlank(message = "Job type is required")
    private String jobType;

    @NotBlank(message = "Skills are required")
    private String skills;

    private Double minSalary;

    private Double maxSalary;

    @NotBlank(message = "Experience level is required")
    private String experienceLevel;

    private LocalDate applicationDeadline;
}