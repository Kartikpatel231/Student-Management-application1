package com.mycompany.studentmanagementapp.controller;


import com.mycompany.studentmanagementapp.excaption.BusinessException;
import com.mycompany.studentmanagementapp.modal.FeebackModel;
import com.mycompany.studentmanagementapp.modal.Response;
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
import org.springframework.web.servlet.view.RedirectView;

@Api
@Controller
@RequestMapping("/api/v1")
public class StudentController {

    private final Logger logger=LoggerFactory.getLogger(this.getClass());


    @Autowired
    private StudentService studentService;
     @CrossOrigin
    @PostMapping("/users")
     public RedirectView login(@RequestBody StudentModal studentModal) throws BusinessException {
      //  ResponseEntity<Boolean> obj=null;
        logger.debug("Entering method login");
        boolean result = studentService.login(studentModal);
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        logger.debug("Exiting method login");
         RedirectView redirectView=new RedirectView();
        //redirectView.setUrl("http://localhost:63342/student-management-app/static/home.html?_ijt=mlersrgefbau196ts3h0320k4");
         redirectView.setUrl("/display");

         return redirectView;
                  //   redirectView.setUrl("http://localhost:9000/Student-Management-App");
         // return redirectView;
    }
    @CrossOrigin
    @PostMapping("/users/register")
    public RedirectView register(@RequestBody StudentModal studentModal)throws BusinessException {

        Long result = studentService.register(studentModal);
        ResponseEntity<Long> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
        System.out.println(responseEntity);
        RedirectView redirectView=new RedirectView();
        //redirectView.setUrl("http://localhost:63342/student-management-app/static/home.html?_ijt=mlersrgefbau196ts3h0320k4");
         redirectView.setUrl("/display");
        return redirectView;
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/users/register/updates")
    public ResponseEntity<Long> updates(@RequestBody StudentModal studentModal) throws BusinessException {

        Long result = studentService.updates(studentModal);
        ResponseEntity<Long> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
        return responseEntity;
    }


    @RequestMapping(method = RequestMethod.DELETE,value = "/users/register/delete/{studentId}")
    public String getDeleted(@PathVariable Long id)throws BusinessException{
        return studentService.getDeleted(id);

    }
    @CrossOrigin
    @PostMapping("/create/profile")
    public Response create(@RequestBody StudentProfileModel studentProfileModel)throws BusinessException {

        studentService.create(studentProfileModel);
        return Response.ok("successfully created",HttpStatus.CREATED);

    }
    @GetMapping("/get/profile/{studentId}")
  //  public String  getProfile(@PathVariable Long studentId)throws  BusinessException{
    //    studentService.getProfile(studentId);
         //return Response.ok("StudentProfile",HttpStatus.CREATED);
     //return "successful";
    public ResponseEntity<Boolean> getStudent(@PathVariable Long studentId) throws BusinessException {

        //logger.debug("Entering method login");
        StudentProfileModel result1 = studentService.getProfile(studentId);
        ResponseEntity<Boolean> responseEntity = new ResponseEntity(result1, HttpStatus.OK);
        return responseEntity;
        //     RedirectView redirectView=new RedirectView();
        // redirectView.setUrl("http://localhost:63342/student-management-app/static/home.html?_ijt=pqtjk2v4kj71b204f73g82sds8");
        //   redirectView.setUrl("http://localhost:9000/Student-Management-App");
        // return redirectView;
    }

    @CrossOrigin
    @PostMapping("/create/feedback")
    public ResponseEntity<Boolean> createFeedback(@RequestBody FeebackModel feebackModel)throws BusinessException {

      FeebackModel feebackModel1=studentService.createFeedback(feebackModel);
        ResponseEntity<Boolean> responseEntity = new ResponseEntity(feebackModel1, HttpStatus.OK);

        return responseEntity;

    }
     }
