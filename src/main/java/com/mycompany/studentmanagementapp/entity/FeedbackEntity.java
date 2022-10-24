package com.mycompany.studentmanagementapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "STUDENT_FEEDBACKS")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)        //value automatic increment by jpa
    private long studentId;
    private String title;
    private String descriptions;

}
