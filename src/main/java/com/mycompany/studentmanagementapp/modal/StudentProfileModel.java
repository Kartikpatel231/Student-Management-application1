package com.mycompany.studentmanagementapp.modal;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)   //it autommatically ignore null value
@JsonInclude(JsonInclude.Include.NON_NULL)

public class StudentProfileModel {

    private Long studentId;

    private String firstName;

    private String fatherName;

    private String motherName;

    private String emailAddress;

    private String bloodGroup;

    private String address1;

    private String aadhaarFilePath;

    private String zipcode;

}
