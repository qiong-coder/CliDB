package com.buaa.CliDB.repository;

import com.buaa.CliDB.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PatientRepository extends MongoRepository<Patient, String> {

    List<Patient> findPatientsByDoctorId(String doctorId);

    long deletePatientById(String id);
}
