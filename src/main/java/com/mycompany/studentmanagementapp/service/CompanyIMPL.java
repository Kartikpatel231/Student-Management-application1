package com.mycompany.studentmanagementapp.service;

import com.mycompany.studentmanagementapp.converter.StudentConveter1;
import com.mycompany.studentmanagementapp.entity.CompanyEntity;
import com.mycompany.studentmanagementapp.modal.CompanyModal;
import com.mycompany.studentmanagementapp.userEntityRepository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyIMPL implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private StudentConveter1 studentConveter1;

    @Override
    public String createCompany(CompanyModal companyModal) {
        if(companyModal != null){
            CompanyEntity companyEntity=studentConveter1.convert(companyModal,CompanyEntity.class);
            companyRepository.save(companyEntity);
            return "Succesfully Created";
        }
        return null;
    }

    @Override
    public CompanyModal getCompany(Long id) {
        if (id != null) {
            CompanyEntity companyEntity = companyRepository.findByCompanyId(id);
            if (companyEntity != null) {
                CompanyModal companyModal = studentConveter1.convert(companyEntity, CompanyModal.class);
                return companyModal;

            }
        }
        return null;

    }

    @Override
    public List<CompanyModal> getAllCompany() {
        List<CompanyEntity> companyEntities=companyRepository.findAll();
        List<CompanyModal> companyModals=studentConveter1.mapList(companyEntities,CompanyModal.class);
        return companyModals;
    }

    @Override
    public String deleteCompany(Long id) {
        if (id != null) {
            CompanyEntity companyEntity=companyRepository.findByCompanyId(id);
            if(companyEntity!=null){
                companyRepository.delete(companyEntity);
                return "successfully Deleted";

            }
            else {
                return "Company Id missing";
            }
        }
        return "parameeter is empty";
    }

    @Override
    public String updateCompany(CompanyModal companyModal) {
        if(companyModal.getCompanyId()==null){
            return "company Id missing";
            }
         CompanyEntity companyEntity1=companyRepository.findByCompanyId(companyModal.getCompanyId());
     if(companyEntity1!=null){
          CompanyEntity companyEntity=studentConveter1.convert(companyModal,companyEntity1.getClass());
        companyRepository.save(companyEntity);

        return "Company updated successfully.";
    } else {
        return "Company with ID " + companyModal.getCompanyId() + " not found.";
    }
    }


}
