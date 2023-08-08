package com.mycompany.studentmanagementapp.userEntityRepository;

import com.mycompany.studentmanagementapp.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
 StudentEntity findByEmailAndPassword(String email, String password);
 StudentEntity findByEmail(String email);
 StudentEntity findByStudentId(Long studentId);
 StudentEntity getOne(Long id);


}
