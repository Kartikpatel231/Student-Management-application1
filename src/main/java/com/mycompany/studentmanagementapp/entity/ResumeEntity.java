package com.mycompany.studentmanagementapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Resume_Table")
@Data
public class ResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resumeId;
    String resumeName="resume";
    String resumeUrl;

}
