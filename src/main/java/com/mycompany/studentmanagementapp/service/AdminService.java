package com.mycompany.studentmanagementapp.service;

import com.mycompany.studentmanagementapp.constant.Status;
import com.mycompany.studentmanagementapp.entity.StudentEntity;
import com.mycompany.studentmanagementapp.modal.DTO;
import com.mycompany.studentmanagementapp.userEntityRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//
//import com.mycompany.studentmanagementapp.entity.ResumeEntity;
//import com.mycompany.studentmanagementapp.userEntityRepository.ResumeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
@Service
public class AdminService {
@Autowired
private StudentRepository studentRepository;

    public List<DTO> getStudentsByStatus(Status status,String companyName){
        List<DTO> dto=new ArrayList<>();
            List<StudentEntity> studentEntitys= studentRepository.findAll().stream()
                    .filter(studentEntity -> studentEntity.getStatus() == status)
                    .filter(studentEntity -> studentEntity.getCompanyEntities().stream()
                            .anyMatch(companyEntity -> companyName.equals(companyEntity.getName())))
                    .collect(Collectors.toList());

            for(StudentEntity studentEntits:studentEntitys){
                DTO obj=new DTO();
                obj.setStudentId(studentEntits.getStudentId());
                obj.setFullName(studentEntits.getFullName());
                obj.setGender(studentEntits.getGender());
                obj.setStatus(studentEntits.getStatus());
                obj.setStudentProfileEntity(studentEntits.getStudentProfileEntity());
                obj.setCompanyEntities(studentEntits.getCompanyEntities());
                dto.add(obj);
            }
            return dto;
        }
    }



    //
//    private final String uploadDir;
//    @Autowired
//    ResumeRepository resumeRepository;
//    @Autowired
//    public FileStorageService(Environment environment) {
//        this.uploadDir = environment.getProperty("file.upload-dir");
//    }
//
//    public String storeFile(MultipartFile file) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        Path filePath = Paths.get(uploadDir).resolve(fileName);
//
//        try (InputStream inputStream = file.getInputStream()) {
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        }
//        ResumeEntity resumeEntity=new ResumeEntity();
//        resumeEntity.setResumeUrl(filePath.toAbsolutePath().toString());
//        resumeRepository.save(resumeEntity);
//        return filePath.toAbsolutePath().toString();
//    }
//}
