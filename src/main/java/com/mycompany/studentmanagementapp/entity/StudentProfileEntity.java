package com.mycompany.studentmanagementapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "STUDENT_PROFILE")
public class StudentProfileEntity {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long profileId;

    private String studentName;

    private String fatherName;

    private String motherName;

    private String mobileNumber;

    private String category;

    private String dateOfBirth;

    private String address1;

    private String bloodGroup;
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "EnrollementNumber")
    private String enrollementNumber;

    private String zipcode;
    private String imagePath;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StudentEntity studentEntity;
}
