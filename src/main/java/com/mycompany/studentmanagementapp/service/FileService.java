package com.mycompany.studentmanagementapp.service;

import com.mycompany.studentmanagementapp.constant.ErrorType;
import com.mycompany.studentmanagementapp.converter.StudentConveter1;
import com.mycompany.studentmanagementapp.entity.ResumeEntity;
import com.mycompany.studentmanagementapp.entity.StudentEntity;
import com.mycompany.studentmanagementapp.entity.StudentProfileEntity;
import com.mycompany.studentmanagementapp.excaption.BusinessException;
import com.mycompany.studentmanagementapp.excaption.ErrorModal;
import com.mycompany.studentmanagementapp.modal.StudentProfileModel;
import com.mycompany.studentmanagementapp.userEntityRepository.ResumeRepository;
import com.mycompany.studentmanagementapp.userEntityRepository.StudentProfileRepository;
import com.mycompany.studentmanagementapp.userEntityRepository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
@Service
@Slf4j
public class FileService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private  ServiceIMPL serviceIMPL;
    @Autowired
    StudentProfileRepository studentProfileRepository;
    @Autowired
    private StudentConveter1 studentConveter1;
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    BlobStorageService blobStorageService;
//    private static final String DOCUMENT_BASE_LOCATION = "D:/svvv placment app admin/placementProAdmin-panel/src/assets/";
    private static final String DOCUMENT_BASE_LOCATION = "https://svvvplacementdata.blob.core.windows.net/";


    public String uploadFile(Long studentId, MultipartFile file, RedirectAttributes redirectAttributes) throws BusinessException {
        if (file == null && file.isEmpty()) {
            throw new RuntimeException("File can't be empty.");
        }
        StudentEntity studentEntity = studentRepository.findByStudentId(studentId);
        if (null == studentEntity) {
            List<ErrorModal> errorList = new ArrayList<>();

            ErrorModal errorModal = new ErrorModal();
            errorModal.setCode(ErrorType.NOT_EXIT.toString());
            errorModal.setMessage("Student Is no present with this Student Id,Please check student Id");

            errorList.add(errorModal);
            throw new BusinessException(errorList);
        }

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        //StudentProfileModel profileEntity = serviceIMPL.getProfile(studentId);
       // StudentProfileEntity studentProfileEntity=studentProfileRepository.findByProfileId(profileEntity.getId());
        String url=blobStorageService.uploadFile(file);
        try {

            // Get the file and save it somewhere
         //   byte[] bytes = file.getBytes();
          //  Path path = Paths.get( DOCUMENT_BASE_LOCATION + file.getOriginalFilename());
           // Files.write(path, bytes);

            if(studentEntity!=null){
                if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
                    //studentProfileEntity.setImagePath(DOCUMENT_BASE_LOCATION+url);
                    studentEntity.setImagePath(DOCUMENT_BASE_LOCATION+"images/"+url);
                    studentRepository.save(studentEntity);
                   // studentProfileRepository.save(studentProfileEntity);
                }
            }
            if (file.getContentType().equals("application/pdf")) {
                ResumeEntity resumeEntity=new ResumeEntity();
                resumeEntity.setResumeUrl(DOCUMENT_BASE_LOCATION+"pdf/"+url);
                 resumeEntity.setCreatedOn(LocalDateTime.now());
                resumeRepository.save(resumeEntity);
                studentEntity.setResumeEntity(resumeEntity);
                studentRepository.save(studentEntity);
            }

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }
}










