package com.mycompany.studentmanagementapp.controller;

import com.mycompany.studentmanagementapp.modal.CompanyModal;
import com.mycompany.studentmanagementapp.service.CompanyService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")//loca
public class CompanyController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CompanyService companyService;

    @PostMapping(name = "/create")
    public ResponseEntity<String> create(@RequestBody CompanyModal companyModal){
         String result=companyService.createCompany(companyModal);
         ResponseEntity<String> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
         return responseEntity;
    }
    @GetMapping(name = "/getAll")
    public ResponseEntity<List<CompanyModal>> get(){
        List<CompanyModal> result=companyService.getAllCompany();
        ResponseEntity<List<CompanyModal>> responseEntity=new ResponseEntity<>(result,HttpStatus.OK);
        return responseEntity;
    }
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody CompanyModal companyModal){
        String result=companyService.updateCompany(companyModal);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping("/get/{companyId}")
    public ResponseEntity<CompanyModal> getById(@PathVariable Long companyId){
        CompanyModal result=companyService.getCompany(companyId);
        ResponseEntity<CompanyModal> responseEntity=new ResponseEntity<>(result,HttpStatus.OK);
        return responseEntity;
    }
    @PostMapping("/delete/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId){
        String result=companyService.deleteCompany(companyId);
        ResponseEntity<String> responseEntity=new ResponseEntity<>(result,HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/get/company/by/{id}")
    public ResponseEntity<List<CompanyModal>> getByStudentId(@PathVariable Long id){
        List<CompanyModal> result=companyService.getCompanyByStudentId(id);
        ResponseEntity<List<CompanyModal>> responseEntity=new ResponseEntity<>(result,HttpStatus.OK);
        return responseEntity;
    }
}