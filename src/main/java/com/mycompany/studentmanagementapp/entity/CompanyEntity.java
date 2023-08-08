package com.mycompany.studentmanagementapp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "COMPANY_TABLE")
public class CompanyEntity {
    @Column(name = "Company_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;
    private String name;
    private String website;
    private String description;
    private boolean registration;
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "student_id") // Reference column for the relationship
//    @JsonIgnoreProperties("companyEntities") // Ignore the companyEntities field during JSON serialization to avoid circular references
//    private StudentEntity studentEntity;
//    @ManyToOne
//    //Adding the name
//    @JoinColumn(name = "Student_Id")
//    StudentEntity studentEntity;
}