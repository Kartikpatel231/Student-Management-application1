package com.mycompany.studentmanagementapp.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)   //it autommatically ignore null value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeebackModel {
    private Long id;
    private String title;
    private String descriptions;
}

