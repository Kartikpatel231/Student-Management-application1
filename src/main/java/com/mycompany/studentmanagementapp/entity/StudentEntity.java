package com.mycompany.studentmanagementapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.studentmanagementapp.constant.Gender;
import com.mycompany.studentmanagementapp.constant.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Student_Table")
@Data
@NoArgsConstructor
public class StudentEntity {
    @Column(name = "Student_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long studentId;
    private String fullName;
    private String mobileNumber;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Status status;

    @JoinColumn(name = "student_profile_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StudentProfileEntity studentProfileEntity;
    @JoinColumn(name = "STUDENT_FEEDBACKS")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FeedbackEntity feedbackEntity;
    @JoinColumn(name = "Resume")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ResumeEntity resumeEntity;

//        @OneToMany(cascade = CascadeType.ALL,targetEntity = CompanyEntity.class)
//    @JoinColumn(name = "student_company",referencedColumnName = "student_id")
//    @JsonIgnore
@ManyToMany
@JoinTable(
        name = "Student_Company",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "company_id")
)
     private Set<CompanyEntity> companyEntities = new HashSet<>();
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentEntity", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<CompanyEntity> companyEntities = new HashSet<>();
//
   public void addCompany(CompanyEntity companyEntity) {
     //   if (companyEntity != null) {
   //         if (companyEntities == null) {
//                companyEntities = new HashSet<>();
//            }
            companyEntities.add(companyEntity);

//        }
   }
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UniversityDetailEntity universityDetailEntity;

}
