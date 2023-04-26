package com.mycompany.studentmanagementapp.controller;
import com.mycompany.studentmanagementapp.excaption.BusinessException;
import com.mycompany.studentmanagementapp.modal.Response;
import com.mycompany.studentmanagementapp.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Api
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;
 @CrossOrigin
    @PostMapping(value = "/upload/{studentId}")
    public Response uploadFile(@PathVariable Long studentId, @RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) throws BusinessException {
        return Response.ok("ds", HttpStatus.CREATED).setPayload(fileService.uploadFile(studentId, file,redirectAttributes));
    }



}