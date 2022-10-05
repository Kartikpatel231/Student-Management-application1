package com.mycompany.studentmanagementapp.entity;

import com.mycompany.studentmanagementapp.constant.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="Student_Table")
@Data                             //getter and setter,equal and hastable,to string
@NoArgsConstructor                //no constructor with arguments
public class StudentEntity {
    @Column(name = "Student_Id")
    @Id                             //denotes to primary key
    @GeneratedValue(strategy = GenerationType.AUTO)        //value automatic increment by jpa
    private long id;
    private String fullName;
    private String  mobileNumber;
    private String email;
    private String password;
    private Gender gender;
    @JoinColumn(name = "student_profile_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StudentProfileEntity studentProfileEntity;
}
