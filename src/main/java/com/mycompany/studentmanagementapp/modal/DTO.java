package com.mycompany.studentmanagementapp.modal;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.studentmanagementapp.constant.Gender;
import com.mycompany.studentmanagementapp.constant.Status;
import com.mycompany.studentmanagementapp.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DTO {
    private long studentId;
    private String fullName;
    private Gender gender;
    private Status status;
    private StudentProfileEntity studentProfileEntity;
    private Set<CompanyEntity> companyEntities;
    private FeedbackEntity feedbackEntity;
    private ResumeEntity resumeEntity;
    private UniversityDetailEntity universityDetailEntity;

}