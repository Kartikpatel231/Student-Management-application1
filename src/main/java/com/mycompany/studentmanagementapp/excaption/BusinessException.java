package com.mycompany.studentmanagementapp.excaption;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BusinessException extends Exception {
    private List<ErrorModal> errorList;
    public BusinessException(List<ErrorModal>errorList){
        this.errorList=errorList;
    }
}
