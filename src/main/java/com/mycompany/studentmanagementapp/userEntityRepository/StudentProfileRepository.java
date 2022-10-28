package com.mycompany.studentmanagementapp.userEntityRepository;

import com.mycompany.studentmanagementapp.entity.StudentProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentProfileRepository extends CrudRepository<StudentProfileEntity ,Long> {
//StudentProfileEntity findByProfileId(Long profileId);
}
