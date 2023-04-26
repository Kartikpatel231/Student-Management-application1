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

    private Long id;

    private String studentName;

    private String fatherName;

    private String motherName;

    private String mobileNumber;

    private String bloodGroup;

    private String dateOfBirth;

    private String category;

    private String address1;

    private String enrollementNumber;

    private String zipcode;

}
