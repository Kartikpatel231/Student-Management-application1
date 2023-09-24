package com.mycompany.studentmanagementapp.service;

import com.mycompany.studentmanagementapp.constant.Status;
import com.mycompany.studentmanagementapp.converter.StudentConveter1;
import com.mycompany.studentmanagementapp.entity.CompanyEntity;
import com.mycompany.studentmanagementapp.entity.StudentEntity;
import com.mycompany.studentmanagementapp.entity.UniversityDetailEntity;
import com.mycompany.studentmanagementapp.modal.DTO;
import com.mycompany.studentmanagementapp.modal.UniversityModel;
import com.mycompany.studentmanagementapp.userEntityRepository.StudentRepository;
import com.mycompany.studentmanagementapp.userEntityRepository.UniversityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityImpl implements UniversityService {
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentConveter1 studentConveter1;
    @Autowired
    private final ModelMapper modelMapper;

    public UniversityImpl(){
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    @Override
    public String createUniversity(UniversityModel universityModel,Long id) {
        StudentEntity studentEntity=studentRepository.findByStudentId(id);
        UniversityDetailEntity universityDetailEntity=studentConveter1.convert(universityModel,UniversityDetailEntity.class);
        studentEntity.setUniversityDetailEntity(universityDetailEntity);
        studentRepository.save(studentEntity);
        return "Created Successfully";
    }

    @Override
    public String deleteUniversity(Long id) {
        universityRepository.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public String updateUniversity(UniversityModel universityModel, Long id) {
        UniversityDetailEntity university=universityRepository.findById(id).get();
        if (university != null) {
            // Update the existing entity with non-null values from universityModel
            modelMapper.map(universityModel, university);

            universityRepository.save(university);
            return "Updated Successfully";
        }

        return "Update Failed";
    }


    @Override
    public UniversityModel getUniversity(Long id) {

        StudentEntity studentEntity=studentRepository.findByStudentId(id);
        UniversityDetailEntity universityDetailEntity=studentEntity.getUniversityDetailEntity();
        //UniversityDetailEntity university=universityRepository.findById(id).get();

        UniversityModel universityModel=studentConveter1.convert(universityDetailEntity,UniversityModel.class);
        return universityModel;
    }

    @Override
    public List<UniversityModel> getAllUniversity() {
        List<UniversityDetailEntity> universityDetailEntities=universityRepository.findAll();
        List<UniversityModel> universityModels=studentConveter1.mapList(universityDetailEntities,UniversityModel.class);
        return universityModels;
    }

    @Override
    public List<DTO> filterByNumber(Double tenthMarks,Double twelfthMarks,Double cgpa,Double sgpa,String name) {
        List<DTO> obj1=new ArrayList<>();

//        List<StudentEntity> studentEntities=studentRepository.findAll().stream().filter(num->(tenthMarks==null || num.getUniversityDetailEntity().getTenthMarks()>=tenthMarks))
//                                                                                .filter(num->(twelfthMarks==null || num.getUniversityDetailEntity().getTwelfthMarks()>=twelfthMarks))
//                                                                                 .filter(num->(cgpa==null || num.getUniversityDetailEntity().getCgpa()>=cgpa))
//                                                                                 .filter(num->(sgpa==null ||num.getUniversityDetailEntity().getSgpa()>=sgpa)).collect(Collectors.toList());
       StudentEntity studentEntity=new StudentEntity();
        List<StudentEntity> studentEntities = studentRepository.findAll()
                .stream()
                .filter(num -> (tenthMarks == null || (num.getUniversityDetailEntity() != null && num.getUniversityDetailEntity().getTenthMarks() >= tenthMarks)))
                .filter(num -> (twelfthMarks == null || (num.getUniversityDetailEntity() != null && num.getUniversityDetailEntity().getTwelfthMarks() >= twelfthMarks)))
                .filter(num -> (cgpa == null || (num.getUniversityDetailEntity() != null && num.getUniversityDetailEntity().getCgpa() >= cgpa)))
                .filter(num -> (sgpa == null || (num.getUniversityDetailEntity() != null && num.getUniversityDetailEntity().getSgpa() >= sgpa)))
                .filter(num -> (name==null)  || (num.getCompanyEntities()!=null && num.getCompanyEntities().stream().anyMatch(companyEntity ->name.equals(companyEntity.getName()))))
                .collect(Collectors.toList());

        for (StudentEntity studentEntities1:studentEntities){
            DTO obj=new DTO();
             studentEntities1.setStatus(Status.APPROVED);
            if (studentEntities1.getCompanyEntities() != null) {
                for (CompanyEntity companyEntity : studentEntities1.getCompanyEntities()) {
                    // Set the companyStatus for each company
                    if(companyEntity.getName().equals(name))
                    companyEntity.setStatus(Status.APPROVED); // Replace with the desired company status value
                }
            }
             studentRepository.save(studentEntities1);
            obj.setCompanyEntities(studentEntities1.getCompanyEntities());
            obj.setFeedbackEntity(studentEntities1.getFeedbackEntity());

            //String filePath = studentEntities1.getResumeEntity().getResumeUrl();
            //String convertedPath = convertSlashes(filePath);
            //studentEntities1.getResumeEntity().setResumeUrl(convertedPath);
            obj.setResumeEntity(studentEntities1.getResumeEntity());

            obj.setStudentProfileEntity(studentEntities1.getStudentProfileEntity());
            obj.setStudentId(studentEntities1.getStudentId());
            obj.setFullName(studentEntities1.getFullName());
            obj.setGender(studentEntities1.getGender());
            obj.setStatus(studentEntities1.getStatus());
            obj.setUniversityDetailEntity(studentEntities1.getUniversityDetailEntity());
            obj1.add(obj);
        }
        return obj1;

    }
}
