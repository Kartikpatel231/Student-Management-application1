package com.mycompany.studentmanagementapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Resume_Table")
@Data
public class ResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resumeId;
    String resumeName="resume";
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    String resumeUrl;

}
