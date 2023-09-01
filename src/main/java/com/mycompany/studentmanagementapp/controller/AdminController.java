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
import org.springframework.web.multipart.MultipartFile;

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
//    @Autowired
//    private FileStorageService fileStorageService;
//
//    @Autowired
//    private ResumeRepository resumeRepository;
//
//    @Autowired
//    private StudentRepository studentRepository;
}
//    @PostMapping("/uploadResume/{studentId}")
//    public ResponseEntity<String> uploadResume(@RequestParam Long studentId, @RequestParam MultipartFile resumeFile) {
//        try {
//            StudentEntity student = studentRepository.findByStudentId(studentId);
//            if (student == null) {
//                return ResponseEntity.badRequest().body("Student not found.");
//            }
//            if (resumeFile.isEmpty() || !resumeFile.getContentType().equals("application/pdf")) {
//                return ResponseEntity.badRequest().body("Only PDF files are allowed.");
//            }
//            // Store the uploaded resume file
//            String fileUrl = fileStorageService.storeFile(resumeFile);
//
//            // Create and save ResumeEntity
//            ResumeEntity resume = new ResumeEntity();
//            resume.setResumeName(resumeFile.getOriginalFilename());
//            resume.setResumeUrl(fileUrl);
//
//            resumeRepository.save(resume);
//
//            return ResponseEntity.ok("Resume uploaded successfully.");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading resume.");
//        }
//    }
//}
