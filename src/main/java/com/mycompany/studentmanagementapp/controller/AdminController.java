package com.mycompany.studentmanagementapp.controller;

import com.mycompany.studentmanagementapp.constant.Status;
import com.mycompany.studentmanagementapp.entity.ResumeEntity;
import com.mycompany.studentmanagementapp.entity.StudentEntity;
import com.mycompany.studentmanagementapp.modal.DTO;
import com.mycompany.studentmanagementapp.service.AdminService;
import com.mycompany.studentmanagementapp.userEntityRepository.ResumeRepository;
import com.mycompany.studentmanagementapp.userEntityRepository.StudentRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Api
@RestController
@RequestMapping("/api/v3")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{status}/approved/{name}")
    public ResponseEntity<List<DTO>> getStudentsByStatus(@RequestParam Status status,@PathVariable String name){
        List<DTO> result=adminService.getStudentsByStatus(status,name);
        ResponseEntity<List<DTO>> responseEntity=new ResponseEntity<>(result,HttpStatus.OK);
        return responseEntity;
    }
    @PostMapping("/auth/admin/{userName}/{password}")
    public ResponseEntity<String> auth(@PathVariable String userName,@PathVariable String password){
        String str="";

        if(!userName.equals("kartik@gmail.com") || !password.equals("kartik@123")){
            if(!userName.equals("kartik@gmail.com")){
                str="userName is incorrect";
            }
            else if(!password.equals("kartik@123")){
                str="password is incorrect";
            }
        }
        else {
              str="successfuly login";
        }
        ResponseEntity<String> obj=new ResponseEntity<>(str,HttpStatus.OK);
        return obj;

    }
}