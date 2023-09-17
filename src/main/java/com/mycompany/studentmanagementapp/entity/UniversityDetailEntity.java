package com.mycompany.studentmanagementapp.entity;

import com.mycompany.studentmanagementapp.constant.Placement;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "UNIVERSITY_DETAILS")
public class UniversityDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "university_details_id")
    private Long id;
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

    private int passingYear; // Change to integer
    @Enumerated(EnumType.STRING)
    private Placement placementStatus;

}

