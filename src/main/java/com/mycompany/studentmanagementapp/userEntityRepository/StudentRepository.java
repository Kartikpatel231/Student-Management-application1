package com.mycompany.studentmanagementapp.userEntityRepository;

import com.mycompany.studentmanagementapp.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
 StudentEntity findByEmailAndPassword(String email, String password);
 StudentEntity findByEmail(String email);

 @Override
 Optional<StudentEntity> findById(Long id);

 StudentEntity getOne(Long id);
}
