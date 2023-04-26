package com.mycompany.studentmanagementapp.validation;


import com.mycompany.studentmanagementapp.constant.ErrorType;
import com.mycompany.studentmanagementapp.excaption.ErrorModal;
import com.mycompany.studentmanagementapp.modal.StudentModal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentValidator {

    public List<ErrorModal> validateRequest(StudentModal studentModal){

        List<ErrorModal> errorModelList=new ArrayList<>();
        if(null != studentModal && studentModal.getEmail() == null){
            ErrorModal errorModal=new ErrorModal();
            errorModal.setCode(ErrorType.NOT_EMPTY.toString());
            errorModal.setMessage("Email cannot be empty");
            errorModelList.add(errorModal);
        }
        if(null != studentModal && studentModal.getPassword()== null){
            ErrorModal errorModal=new ErrorModal();
            errorModal.setCode(ErrorType.NOT_EMPTY.toString());
            errorModal.setMessage("Password cannot be empty");
            errorModelList.add(errorModal);
        }
        return errorModelList;
    }
}
