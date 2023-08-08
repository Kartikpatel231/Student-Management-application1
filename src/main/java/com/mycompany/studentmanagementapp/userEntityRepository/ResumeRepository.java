package com.mycompany.studentmanagementapp.userEntityRepository;

import com.mycompany.studentmanagementapp.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<ResumeEntity,Long> {
}
