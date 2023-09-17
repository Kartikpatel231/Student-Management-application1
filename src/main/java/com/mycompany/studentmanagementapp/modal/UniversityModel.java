package com.mycompany.studentmanagementapp.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.studentmanagementapp.constant.Placement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)   //it autommatically ignore null value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UniversityModel {
    private String universityName; // Allow dynamic university names
    private double tenthMarks;
    private double twelfthMarks;
    private double cgpa;
    private double sgpa;
    private int currentYear; // Change to integer
    private int currentSemester; // Change to integer
    private int numberOfBacklogs; // Change to integer
    private String course;
    private String section;
    private String batches;
    @Enumerated(EnumType.STRING)
    private Placement placementStatus;
    private int passingYear; // Change to integer
}
