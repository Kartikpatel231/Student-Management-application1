package com.mycompany.studentmanagementapp.service;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mycompany.studentmanagementapp.constant.ErrorType;
import com.mycompany.studentmanagementapp.constant.Placement;
import com.mycompany.studentmanagementapp.constant.Status;
import com.mycompany.studentmanagementapp.converter.StudentConveter1;
import com.mycompany.studentmanagementapp.entity.CompanyEntity;
import com.mycompany.studentmanagementapp.entity.FeedbackEntity;
import com.mycompany.studentmanagementapp.entity.StudentEntity;
import com.mycompany.studentmanagementapp.entity.StudentProfileEntity;
import com.mycompany.studentmanagementapp.excaption.BusinessException;
import com.mycompany.studentmanagementapp.excaption.ErrorModal;
import com.mycompany.studentmanagementapp.modal.*;
import com.mycompany.studentmanagementapp.userEntityRepository.CompanyRepository;
import com.mycompany.studentmanagementapp.userEntityRepository.StudentProfileRepository;
import com.mycompany.studentmanagementapp.userEntityRepository.StudentRepository;
import com.mycompany.studentmanagementapp.validation.StudentValidator;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     @Autowired
    private CompanyRepository companyRepository;
     @Autowired
     private MailJetService mailJetService;
    @Override
    public long login(StudentModal userModal) throws BusinessException {
        logger.debug("Entering method login");

        List<ErrorModal> errorModelList = studentValidator.validateRequest(userModal);
        if (!CollectionUtils.isEmpty(errorModelList)) {
            throw new BusinessException(errorModelList);
        }
        long result;
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
            result = userEntity.getStudentId();
        }
        logger.debug("Exiting method login");
        return result;
    }
    @SneakyThrows
    public StudentModal register(StudentModal userModal) throws BusinessException, MailjetSocketTimeoutException, MailjetException {

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
   //    String str= mailJetService.sendEmail(userModal.getFullName(),userModal.getEmail());
        try {

            String str = mailJetService.sendEmail(userModal.getFullName(), userModal.getEmail());
        } catch (MailjetException | MailjetSocketTimeoutException e) {
            // Log the exception and an error message
            logger.error("Error sending email:", e);
            // You can choose to return a specific error message or take other actions.
        }
        userEntity.setStatus(Status.PENDING);
        StudentEntity userEntity1 = studentRepository.save(userEntity);
        StudentModal studentModal1=new StudentModal();
        studentModal1.setFullName(userEntity1.getFullName());
        studentModal1.setStudentId(userEntity1.getStudentId());
        studentModal1.setUrl("http://localhost:8080/home2.html");
        return studentModal1;
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
        return userEntity1.getStudentId();

    }

    public String getDeleted(Long id) throws BusinessException {
        StudentEntity userEntity= studentRepository.getOne(id);
        studentRepository.delete(userEntity);
        return "deleted Successfull" ;
        }

    @Override
    public StudentProfileModel create(StudentProfileModel studentProfileModel) throws BusinessException {
        //StudentEntity studentEntity=new StudentEntity();
       StudentEntity studentEntity = studentRepository.findByStudentId(studentProfileModel.getId());
        if (null == studentEntity) {
            List<ErrorModal> errorList = new ArrayList<>();

            ErrorModal errorModal = new ErrorModal();
            errorModal.setCode(ErrorType.NOT_EXIT.toString());
            errorModal.setMessage("Student Is no present with this Student Id,Please check student Id");

            errorList.add(errorModal);
            throw new BusinessException(errorList);

        }
        StudentProfileEntity studentProfileEntity=studentConveter1.convert(studentProfileModel,StudentProfileEntity.class);
         studentEntity.setStudentProfileEntity(studentProfileEntity);
         studentRepository.save(studentEntity);
       //  studentProfileRepository.save(studentProfileEntity);
        return studentProfileModel;
    }

    @Override
    public StudentProfileModel getProfile(Long studentId) throws BusinessException {

       // StudentProfileEntity studentProfileEntity = studentProfileRepository.findByProfileId(profileId);
        StudentEntity studentEntity = studentRepository.findByStudentId(studentId);

        if (null == studentEntity) {
            List<ErrorModal> errorList = new ArrayList<>();

            ErrorModal errorModal = new ErrorModal();
            errorModal.setCode(ErrorType.NOT_EXIT.toString());
            errorModal.setMessage("Student Is no present with this Student Id,Please check student Id");

            errorList.add(errorModal);
            throw new BusinessException(errorList);

        }

         StudentProfileModel student= studentConveter1.convert(studentEntity.getStudentProfileEntity(),StudentProfileModel.class);
           return student;


    }

    @Override
    public StudentProfileModel getProfileByEnrol(String studentEnrollment) throws BusinessException {
        StudentProfileEntity studentProfileEntity=studentProfileRepository.findByEnrollementNumber(studentEnrollment);
        if (null == studentProfileEntity) {
            List<ErrorModal> errorList = new ArrayList<>();

            ErrorModal errorModal = new ErrorModal();
            errorModal.setCode(ErrorType.NOT_EXIT.toString());
            errorModal.setMessage("Student Is no present with this Student Id,Please check student Id");

            errorList.add(errorModal);
            throw new BusinessException(errorList);

        }

        StudentProfileModel student= studentConveter1.convert(studentProfileEntity,StudentProfileModel.class);
        return student;

    }

    public FeebackModel createFeedback(FeebackModel feebackModel) throws BusinessException {

        StudentEntity studentEntity = studentRepository.findByStudentId(feebackModel.getId());
        if (null == studentEntity) {
            List<ErrorModal> errorList = new ArrayList<>();

            ErrorModal errorModal = new ErrorModal();
            errorModal.setCode(ErrorType.NOT_EXIT.toString());
            errorModal.setMessage("Student Is no present with this Student Id,Please check student Id");

            errorList.add(errorModal);
            throw new BusinessException(errorList);

        }
        FeedbackEntity feedbackEntity=studentConveter1.convert(feebackModel,FeedbackEntity.class);
        studentEntity.setFeedbackEntity(feedbackEntity);
        studentRepository.save(studentEntity);
        return feebackModel;
    }
    @Override
    public String applyToCompany( Long companyId,Long id) {
        if (id == null || companyId == null) {
            return "id is not found";
        }


        StudentEntity studentEntity=studentRepository.findByStudentId(id);
        // Example code to create a company and associate it with a student

//        CompanyEntity companyEntity=studentConveter1.convert(companyModal,CompanyEntity.class);
         CompanyEntity companyEntity = companyRepository.findByCompanyId(companyId);
         companyEntity.setPlacementStatus(Placement.In_Process);
         companyEntity.setStatus(Status.PENDING);
        if(studentEntity.getCompanyEntities()==null) {
            Set<CompanyEntity> companyEntities = new HashSet<>();
            companyEntities.add(companyEntity);
            studentEntity.setCompanyEntities(companyEntities);
        }
        else {
            studentEntity.addCompany(companyEntity);
        }
        //for (CompanyEntity companyEntity1:studentEntity.getCompanyEntities()){
          //     companyEntity1
        // Save the updated student with the associated company
        studentRepository.save(studentEntity);

        return "Applied successfully";
    }


    @Override
    public List<DTO> getAll() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<DTO> obj1 = new ArrayList<>();
        for (StudentEntity studentEntities1:studentEntities){
            DTO obj=new DTO();

            obj.setCompanyEntities(studentEntities1.getCompanyEntities());
             obj.setFeedbackEntity(studentEntities1.getFeedbackEntity());

             //String filePath = studentEntities1.getResumeEntity().getResumeUrl();
             //String convertedPath = convertSlashes(filePath);
             //studentEntities1.getResumeEntity().setResumeUrl(convertedPath);
             obj.setResumeEntity(studentEntities1.getResumeEntity());

             obj.setStudentProfileEntity(studentEntities1.getStudentProfileEntity());
             obj.setStudentId(studentEntities1.getStudentId());
             obj.setFullName(studentEntities1.getFullName());
             obj.setGender(studentEntities1.getGender());
             obj.setStatus(studentEntities1.getStatus());
             obj.setUniversityDetailEntity(studentEntities1.getUniversityDetailEntity());
             obj1.add(obj);
         }
         return obj1;

    }
   // public static String convertSlashes(String path) {
     //   return path.replace("\\", "/");
   // }
    @Override
    public List<StudentEntity> getAllStudentsByCompany(String name) {
        CompanyEntity companyEntity=companyRepository.findByName(name);
        List<StudentEntity> studentEntities=studentRepository.findByCompanyEntitiesContains(companyEntity);
        return  studentEntities;
    }

    @Override
    public String updateStatus(Long id,Status status) {
        StudentEntity studentEntity=studentRepository.findByStudentId(id);
        studentEntity.setStatus(status);
        studentRepository.save(studentEntity);
        return "updated";
    }


    public StudentProfileEntity getStudentProfileByStudentId(Long studentId) {
        return studentProfileRepository.findByStudentEntityStudentId(studentId);
    }
}