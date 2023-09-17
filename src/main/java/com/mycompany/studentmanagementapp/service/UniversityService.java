package com.mycompany.studentmanagementapp.service;

import com.mycompany.studentmanagementapp.modal.DTO;
import com.mycompany.studentmanagementapp.modal.UniversityModel;

import java.util.List;

public interface UniversityService{
    String createUniversity(UniversityModel universityModel,Long id);
    String deleteUniversity(Long id);
    String updateUniversity(UniversityModel universityModel,Long id);
    UniversityModel getUniversity(Long id);
    List<UniversityModel> getAllUniversity();
    List<DTO> filterByNumber(Double tenthMarks,Double twelfthMarks,Double cgpa,Double sgpa);
}
