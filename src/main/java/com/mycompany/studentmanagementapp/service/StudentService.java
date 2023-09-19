package com.mycompany.studentmanagementapp.service;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mycompany.studentmanagementapp.constant.Status;
import com.mycompany.studentmanagementapp.entity.StudentEntity;
import com.mycompany.studentmanagementapp.excaption.BusinessException;
import com.mycompany.studentmanagementapp.modal.*;

import java.util.List;

public interface StudentService {
     long login(StudentModal studentModal) throws BusinessException;
      StudentModal register(StudentModal studentModal) throws BusinessException, MailjetSocketTimeoutException, MailjetException;
      Long updates(StudentModal studentModal) throws  BusinessException;
      String getDeleted(Long id) throws  BusinessException;
      StudentProfileModel create(StudentProfileModel studentProfileModel) throws BusinessException;

       StudentProfileModel getProfile(Long studentId) throws BusinessException;
       StudentProfileModel getProfileByEnrol(String studentEnrollment) throws BusinessException;
       FeebackModel createFeedback(FeebackModel feebackModel)throws  BusinessException;
       String applyToCompany(Long companyId,Long id);
       List<DTO> getAll();
       List<StudentEntity> getAllStudentsByCompany(String name);

       String updateStatus(Long id,Status status);
}
