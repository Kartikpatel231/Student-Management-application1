package com.mycompany.studentmanagementapp.service;

import com.mycompany.studentmanagementapp.excaption.BusinessException;
import com.mycompany.studentmanagementapp.modal.FeebackModel;
import com.mycompany.studentmanagementapp.modal.StudentModal;
import com.mycompany.studentmanagementapp.modal.StudentProfileModel;

import java.util.List;

public interface StudentService {
     boolean login(StudentModal studentModal) throws BusinessException; //throws BusinessException;
      Long register(StudentModal studentModal) throws BusinessException; //throws BusinessException;
      Long updates(StudentModal studentModal) throws  BusinessException;
      String getDeleted(Long id) throws  BusinessException;

       StudentProfileModel create(StudentProfileModel studentProfileModel) throws BusinessException;
       StudentProfileModel getProfile(Long studentId) throws BusinessException;
       FeebackModel createFeedback(FeebackModel feebackModel)throws  BusinessException;
}
