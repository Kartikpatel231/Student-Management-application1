package com.mycompany.studentmanagementapp.controller;

import com.mycompany.studentmanagementapp.excaption.BusinessException;
import com.mycompany.studentmanagementapp.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/v3")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @PostMapping("/create")
    public String create(@PathVariable Long studentId, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws BusinessException {


        // Check if the file size is within limits (if needed)
        // Implement any additional validation logic if required

        // Call the service method to create the resume and get the resume URL
       // String resumeUrl = resumeService.createResume(studentId, file);

        // You can perform further operations or redirection here if needed
        // For now, just return the resume URL as a response
        return "";
    }
}
