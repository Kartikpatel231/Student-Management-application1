package com.mycompany.studentmanagementapp.service;

import com.mycompany.studentmanagementapp.modal.CompanyModal;

import java.util.List;

public interface CompanyService {
    String createCompany(CompanyModal companyModal);
    CompanyModal getCompany(Long id);
    List<CompanyModal> getAllCompany();
    List<CompanyModal> getCompanyByStudentId(Long id);
    String deleteCompany(Long id);
    String updateCompany(CompanyModal companyModal);
}
