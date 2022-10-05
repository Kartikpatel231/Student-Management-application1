package com.mycompany.studentmanagementapp.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.studentmanagementapp.constant.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)   //it autommatically ignore null value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentModal {
    private String fullName;
    private String  mobileNumber;
    private String email;
    private String password;
    private Gender gender;
}
