package com.mycompany.studentmanagementapp.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.studentmanagementapp.entity.CompanyEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CompanyModal {
    private Long companyId;
    private String name;
    private String website;
    private String description;
    private boolean registration;
}
