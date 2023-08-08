package com.mycompany.studentmanagementapp.service;

import com.mycompany.studentmanagementapp.userEntityRepository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

}
