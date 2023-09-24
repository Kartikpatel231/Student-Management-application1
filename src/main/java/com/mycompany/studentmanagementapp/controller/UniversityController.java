package com.mycompany.studentmanagementapp.controller;

import com.mycompany.studentmanagementapp.modal.DTO;
import com.mycompany.studentmanagementapp.modal.UniversityModel;
import com.mycompany.studentmanagementapp.service.UniversityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/api/v4")
public class UniversityController {
    @Autowired
    private UniversityService universityService;
    @CrossOrigin
    @PostMapping("/create/{id}/uni")
    public ResponseEntity<String> create(@PathVariable Long id,@RequestBody UniversityModel universityModel){
        String result=universityService.createUniversity(universityModel,id);
        ResponseEntity<String> obj=new ResponseEntity<>(result,HttpStatus.CREATED);
        return obj;
    }
    @CrossOrigin
    @GetMapping("/get/{id}")
    public ResponseEntity<UniversityModel> get(@PathVariable Long id){
        UniversityModel universityModel=universityService.getUniversity(id);
        ResponseEntity<UniversityModel> obj=new ResponseEntity<>(universityModel,HttpStatus.OK);
        return obj;
    }
    @GetMapping("/getAll")
    public  ResponseEntity<List<UniversityModel>> getAll(){
        List<UniversityModel> universityModels=universityService.getAllUniversity();
        ResponseEntity<List<UniversityModel>> obj=new ResponseEntity<>(universityModels,HttpStatus.OK);
        return obj;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        String result=universityService.deleteUniversity(id);
        ResponseEntity<String> obj=new ResponseEntity<>(result,HttpStatus.OK);
        return obj;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@RequestBody UniversityModel universityModel){
        String result=universityService.updateUniversity(universityModel,id);
        ResponseEntity<String> obj=new ResponseEntity<>(result,HttpStatus.OK);
        return obj;
    }

    @CrossOrigin
    @GetMapping(name = "/filter/data")
    public ResponseEntity<List<DTO>> fileterByNumber(@RequestParam(name = "tenthMarks",required = false) Double tenthMarks
                                                      ,@RequestParam(name = "twelfthMarks",required = false) Double twelfthMarks,
                                                       @RequestParam(name = "cgpa",required = false) Double cgpa,
                                                       @RequestParam(name = "sgpa",required = false)Double sgpa,
                                                        @RequestParam(name="name",required = false)String name){
        List<DTO> result=universityService.filterByNumber(tenthMarks,twelfthMarks,cgpa,sgpa,name);
        ResponseEntity<List<DTO>> obj=new ResponseEntity<>(result,HttpStatus.OK);
        return obj;
    }

}
