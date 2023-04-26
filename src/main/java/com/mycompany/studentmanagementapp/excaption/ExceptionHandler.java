package com.mycompany.studentmanagementapp.excaption;

import com.mycompany.studentmanagementapp.constant.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler {

    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<List<ErrorModal>> hadleBusinessException(BusinessException b) {

        for(ErrorModal errorModal: b.getErrorList()) {
            logger.debug("Inside handleBussiness exception {} , {}", errorModal.getCode(),errorModal.getMessage());
        }
        ResponseEntity<List<ErrorModal>> responseEntity
                = new ResponseEntity<List<ErrorModal>>(b.getErrorList(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<List<ErrorModal>> hadleAllException(Exception b) {


        List<ErrorModal> errorModelList=new ArrayList<>();
        ErrorModal errorModal=new ErrorModal();
        //errorModal.setCode(b.getStackTrace().toString());
        errorModal.setCode(ErrorType.UNKNOWN_SERVER_ERROR.toString());
        errorModal.setMessage("unknow server Error");
        //errorModal.setMessage(b.getMessage());

        logger.debug("Inside handleAllException {} , {}", errorModal.getCode(),errorModal.getMessage());
        errorModelList.add(errorModal);

        logger.debug("Inside handleAllException");
        ResponseEntity<List<ErrorModal>> responseEntity
                = new ResponseEntity<List<ErrorModal>>( errorModelList,HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
