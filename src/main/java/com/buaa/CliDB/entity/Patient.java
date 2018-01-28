package com.buaa.CliDB.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Patient")
public class Patient {

    @Id private String id;

    private String doctorId;

    private String name;

    private Integer age;

    private String sex;

    private String medicalRecordNum;

    private String tel;

    private String email;

    private String address;

    private String phoneNum;

    private String newestDiseaseCourseDate;

}
