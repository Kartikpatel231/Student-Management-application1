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

    private String firstName;

    private String fatherName;

    private String motherName;

    private String emailAddress;

    private String address1;

    private String address2;

    private String bloodGroup;

    @Column(name = "aadhaar_Card")
    private String aadhaarFilePath;

    private String zipcode;
}
