package com.mycompany.studentmanagementapp.service;

import com.mycompany.studentmanagementapp.constant.ErrorType;
import com.mycompany.studentmanagementapp.converter.StudentConveter1;
import com.mycompany.studentmanagementapp.entity.StudentEntity;
import com.mycompany.studentmanagementapp.entity.StudentProfileEntity;
import com.mycompany.studentmanagementapp.excaption.BusinessException;
import com.mycompany.studentmanagementapp.excaption.ErrorModal;
import com.mycompany.studentmanagementapp.modal.StudentModal;
import com.mycompany.studentmanagementapp.modal.StudentProfileModel;
import com.mycompany.studentmanagementapp.userEntityRepository.StudentProfileRepository;
import com.mycompany.studentmanagementapp.userEntityRepository.StudentRepository;
import com.mycompany.studentmanagementapp.validation.StudentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceIMPL implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentConveter1 studentConveter1;
    @Autowired
    private StudentValidator studentValidator;
     @Autowired
     private StudentProfileRepository studentProfileRepository;
    @Override
    public boolean login(StudentModal userModal) throws BusinessException {
        logger.debug("Entering method login");

        List<ErrorModal> errorModelList = studentValidator.validateRequest(userModal);
        if (!CollectionUtils.isEmpty(errorModelList)) {
            throw new BusinessException(errorModelList);
        }
        boolean result = false;
        StudentEntity userEntity = studentRepository.findByEmailAndPassword(userModal.getEmail(), userModal.getPassword());

        if (userEntity == null) {

            List<ErrorModal> errorList = new ArrayList<>();

            ErrorModal errorModal = new ErrorModal();
            errorModal.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
            errorModal.setMessage("Incorrect email or password");

            errorList.add(errorModal);
            logger.debug("Invalid login attempt");
            throw new BusinessException(errorList);
        } else {
            logger.debug("Login was success");
            result = true;
        }
        logger.debug("Exiting method login");
        return result;
    }

    public Long register(StudentModal userModal) throws BusinessException {

        List<ErrorModal> errorModelList = studentValidator.validateRequest(userModal);

        if (!CollectionUtils.isEmpty(errorModelList)) {
            throw new BusinessException(errorModelList);
        }
        StudentEntity userEntity = studentConveter1.convert(userModal,StudentEntity.class);
        StudentEntity ue = studentRepository.findByEmail(userModal.getEmail());
        if (null != ue) {
            List<ErrorModal> errorList = new ArrayList<>();

            ErrorModal errorModal = new ErrorModal();
            errorModal.setCode(ErrorType.ALREADY_EXIT.toString());
            errorModal.setMessage("User with this email already exist try another email");

            errorList.add(errorModal);
            throw new BusinessException(errorList);

        }
        StudentEntity userEntity1 = studentRepository.save(userEntity);
        return userEntity1.getId();
    }

    @Override
    public Long updates(StudentModal userModal) throws BusinessException {

        //list check of email and password
        List<ErrorModal> errorModelList = studentValidator.validateRequest(userModal);

        if (!CollectionUtils.isEmpty(errorModelList)) {
            throw new BusinessException(errorModelList);
        }
        StudentEntity userEntity = studentConveter1.convert(userModal,StudentEntity.class);
        //check if user already exist
        StudentEntity ue = studentRepository.findByEmail(userModal.getEmail());
        if (null != ue) {
            List<ErrorModal> errorList = new ArrayList<>();

            ErrorModal errorModal = new ErrorModal();
            errorModal.setCode(ErrorType.ALREADY_EXIT.toString());
            errorModal.setMessage("User with this email already exist try another email");

            errorList.add(errorModal);
            throw new BusinessException(errorList);

        }

        StudentEntity userEntity1 = studentRepository.save(userEntity);
        return userEntity1.getId();

    }

    public String getDeleted(Long id) throws BusinessException {
        StudentEntity userEntity= studentRepository.getOne(id);
        studentRepository.delete(userEntity);
        return "deleted Successfull" ;
        }

    @Override
    public StudentProfileModel create(StudentProfileModel studentProfileModel) throws BusinessException {
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentProfileModel.getStudentId());
        if (null != studentEntity) {
            List<ErrorModal> errorList = new ArrayList<>();

            ErrorModal errorModal = new ErrorModal();
            errorModal.setCode(ErrorType.NOT_EXIT.toString());
            errorModal.setMessage("Student Is no present with this Student Id,Please check student Id");

            errorList.add(errorModal);
            throw new BusinessException(errorList);

        }
        StudentProfileEntity studentProfileEntity=studentConveter1.convert(studentProfileModel,StudentProfileEntity.class);
        studentProfileRepository.save(studentProfileEntity);
        return studentProfileModel;
    }

    @Override
    public StudentProfileModel getProfile(Long studentId) throws BusinessException {
        StudentProfileEntity studentProfileEntity=new StudentProfileEntity();
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        if (null != studentEntity) {
            List<ErrorModal> errorList = new ArrayList<>();

            ErrorModal errorModal = new ErrorModal();
            errorModal.setCode(ErrorType.NOT_EXIT.toString());
            errorModal.setMessage("Student Is no present with this Student Id,Please check student Id");

            errorList.add(errorModal);
            throw new BusinessException(errorList);

        }
         StudentProfileModel studentProfileModel=studentConveter1.convert(studentProfileEntity,StudentProfileModel.class);
         return studentProfileModel;

    }

}