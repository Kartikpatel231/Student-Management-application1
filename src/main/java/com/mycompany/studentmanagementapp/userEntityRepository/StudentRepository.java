package com.mycompany.studentmanagementapp.userEntityRepository;

import com.mycompany.studentmanagementapp.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
 StudentEntity findByEmailAndPassword(String email, String password);
 StudentEntity findByEmail(String email);
StudentEntity findByStudentId(Long studentId);
 //@Override
 //Optional<StudentEntity> findById(Long studentId);
 //StudentEntity findById(Long studentId);
 StudentEntity getOne(Long id);
}
