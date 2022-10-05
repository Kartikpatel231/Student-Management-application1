package com.mycompany.studentmanagementapp.controller;


import com.mycompany.studentmanagementapp.excaption.BusinessException;
import com.mycompany.studentmanagementapp.modal.StudentModal;
import com.mycompany.studentmanagementapp.modal.StudentProfileModel;
import com.mycompany.studentmanagementapp.service.StudentService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Api
@Controller
@RequestMapping("/api/v1")
public class StudentController {

    private final Logger logger=LoggerFactory.getLogger(this.getClass());


    @Autowired
    private StudentService studentService;
     @CrossOrigin
    @PostMapping("/users")


    public ResponseEntity<Boolean> login(@RequestBody StudentModal studentModal) throws BusinessException {

        logger.debug("Entering method login");
        boolean result = studentService.login(studentModal);
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        logger.debug("Exiting method login");
        return responseEntity;
    }
    @CrossOrigin
    @PostMapping("/users/register")
    public ResponseEntity<Long> register(@RequestBody StudentModal studentModal)throws BusinessException {

        Long result = studentService.register(studentModal);
        ResponseEntity<Long> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/users/register/updates")
    public ResponseEntity<Long> updates(@RequestBody StudentModal studentModal) throws BusinessException {

        Long result = studentService.updates(studentModal);
        ResponseEntity<Long> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
        return responseEntity;
    }


    @RequestMapping(method = RequestMethod.DELETE,value = "/users/register/delete/{id}")
    public String getDeleted(@PathVariable Long id)throws BusinessException{
        return studentService.getDeleted(id);

    }
    @PostMapping("/create/profile")
    public ResponseEntity<Long> create(@RequestBody StudentProfileModel studentProfileModel)throws BusinessException {

        studentService.create(studentProfileModel);
        ResponseEntity<Long> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping("/get/profile/{studentId}")
    public  ResponseEntity<Long> getProfile(@PathVariable Long studentId)throws  BusinessException{
         studentService.getProfile(studentId);
         ResponseEntity<Long> responseEntity=new ResponseEntity<>(HttpStatus.ACCEPTED);
      return  responseEntity;
     }

}