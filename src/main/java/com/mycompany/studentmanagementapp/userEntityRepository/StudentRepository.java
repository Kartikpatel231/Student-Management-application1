package com.mycompany.studentmanagementapp.userEntityRepository;

import com.mycompany.studentmanagementapp.entity.CompanyEntity;
import com.mycompany.studentmanagementapp.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
 StudentEntity findByEmailAndPassword(String email, String password);
 StudentEntity findByEmail(String email);
 StudentEntity findByStudentId(Long studentId);
 StudentEntity getOne(Long id);
 List<StudentEntity> findByCompanyEntitiesContains(CompanyEntity companyEntity);


}
