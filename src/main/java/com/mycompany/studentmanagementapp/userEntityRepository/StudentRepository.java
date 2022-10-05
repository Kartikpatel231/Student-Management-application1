package com.mycompany.studentmanagementapp.userEntityRepository;

import com.mycompany.studentmanagementapp.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
 StudentEntity findByEmailAndPassword(String email, String password);
 StudentEntity findByEmail(String email);

 StudentEntity getOne(Long id);
}
