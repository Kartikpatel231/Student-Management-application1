package com.mycompany.studentmanagementapp.controller;


import com.mycompany.studentmanagementapp.constant.Status;
import com.mycompany.studentmanagementapp.entity.StudentEntity;
import com.mycompany.studentmanagementapp.excaption.BusinessException;
import com.mycompany.studentmanagementapp.modal.*;
import com.mycompany.studentmanagementapp.service.StudentService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Api
@Controller
@RequestMapping("/api/v1")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private StudentService studentService;

    @CrossOrigin
    @PostMapping("/users")
    public RedirectView login(@RequestBody StudentModal studentModal) throws BusinessException {
        logger.debug("Entering method login");
        boolean result = studentService.login(studentModal);
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        logger.debug("Exiting method login");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/display");
        return redirectView;
    }

    @CrossOrigin
    @PostMapping("/users/register")
    public ResponseEntity<StudentModal> register(@RequestBody StudentModal studentModal) throws BusinessException {
        StudentModal result = studentService.register(studentModal);
        RedirectView redirectView = new RedirectView();
        ResponseEntity<StudentModal> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
        redirectView.setUrl("http://localhost:8080/home2.html");

        return responseEntity;
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/users/register/updates")
    public ResponseEntity<Long> updates(@RequestBody StudentModal studentModal) throws BusinessException {
        Long result = studentService.updates(studentModal);
        ResponseEntity<Long> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
        return responseEntity;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/users/register/delete/{studentId}")
    public String getDeleted(@PathVariable Long id) throws BusinessException {
        return studentService.getDeleted(id);

    }

    @CrossOrigin
    @PostMapping("/create/profile")
    public ResponseEntity<StudentProfileModel> create(@RequestBody StudentProfileModel studentProfileModel) throws BusinessException {
       StudentProfileModel studentProfileModel1= studentService.create(studentProfileModel);
        ResponseEntity<StudentProfileModel> responseEntity = new ResponseEntity<>(studentProfileModel1, HttpStatus.CREATED);
        return responseEntity;
    }

    @CrossOrigin(origins="*")
    @GetMapping("/get/profile/{studentId}")
        public ResponseEntity<Boolean> getStudent(@PathVariable Long studentId) throws BusinessException {
        StudentProfileModel result1 = studentService.getProfile(studentId);
        ResponseEntity<Boolean> responseEntity = new ResponseEntity(result1, HttpStatus.OK);
        return responseEntity;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/get/profile/enroll/{studentEnrollment}")
    public ResponseEntity<Boolean> getStudentByEnroll(@PathVariable String studentEnrollment) throws BusinessException{
        StudentProfileModel result2=studentService.getProfileByEnrol(studentEnrollment);
        ResponseEntity<Boolean> responseEntity=new ResponseEntity(result2,HttpStatus.OK);
        return responseEntity;
    }
    @CrossOrigin
    @PostMapping("/create/feedback")
    public ResponseEntity<Boolean> createFeedback(@RequestBody FeebackModel feebackModel) throws BusinessException {
        FeebackModel feebackModel1 = studentService.createFeedback(feebackModel);
        ResponseEntity<Boolean> responseEntity = new ResponseEntity(feebackModel1, HttpStatus.OK);
        return responseEntity;

    }
    @CrossOrigin(origins = "*")
    @PostMapping("/applied/{companyId}/by/{id}")
    public ResponseEntity<String> applied(@PathVariable Long companyId,@PathVariable Long id ){
        String result=studentService.applyToCompany(companyId,id);
        ResponseEntity<String> responseEntity=new ResponseEntity<>(result,HttpStatus.OK);
        return responseEntity;
    }
    @CrossOrigin
    @GetMapping("/getAll/student")
    public ResponseEntity<List<DTO>> getAllStudent(){
        List<DTO> studentEntities=studentService.getAll();
        ResponseEntity<List<DTO>> listResponseEntity=new ResponseEntity<>(studentEntities,HttpStatus.OK);
        return listResponseEntity;
    }

    @GetMapping("get/all/bycompany/{name}")
    public ResponseEntity<List<StudentEntity>> getAllStudents(@PathVariable String name) {
        List<StudentEntity> students = studentService.getAllStudentsByCompany(name);
        if (!students.isEmpty()) {
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PutMapping("/status/{Student_id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long Student_id){
        String result=studentService.updateStatus(Student_id,Status.APPROVED);
        ResponseEntity<String> responseEntity=new ResponseEntity<>(result,HttpStatus.OK);
        return responseEntity;
    }
}
